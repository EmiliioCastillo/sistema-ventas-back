package com.projectjava.demosclient.controllers;


import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.projectjava.demosclient.dto.PagoDTO;
import com.projectjava.demosclient.dto.PayloadDTO;
import com.projectjava.demosclient.entity.HistorialPago;
import com.projectjava.demosclient.services.paymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RestController
@RequestMapping("api/v1/pagos")
@Controller
public class PagosController {

    @Autowired
    PaymentService paymentServices;

    //Recordemos que esta access token es del vendedor, cuando nosotros usamos el accessTOken del vendedor
    //Que en este caso seriamos nosotros, el initPOint que devuelve la solicitud post es para el cliente en ese caso
    //Ahora, en el crear lo qeu se hace es crear un item de pago digamos, es decir la suscripcion que nosotors vmaos a pagar
    //Cuando nosotros creamos eso, se mapea a la solicitud y nos devuelve el initPoint, ese initPoint el cliente lo ve
    //y debe seleccionar la forma a pagar
    //En este caso no es necesario inidicar algun CBU o algo porque nosotros ya estamos utilizando el token
    //Que en este caso el token es de nuestra cuenta de mercado Pago

    private static final String MERCADOPAGO_API_KEY = "TEST-2668407226964700-041412-0ce7e26877279c9435afccef7d4b6725-1771225406";

    @PostMapping("/crear")
    public ResponseEntity<?> crearPago(@RequestBody PagoDTO pagoDTO) {
        MercadoPagoConfig.setAccessToken(MERCADOPAGO_API_KEY);

        String idPago = UUID.randomUUID().toString();
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id(idPago)
                        .title(pagoDTO.getTitulo())
                        .description(pagoDTO.getDescripcion())
                        .categoryId(pagoDTO.getCategoria())
                        .quantity(1)
                        .currencyId("ARS")
                        .unitPrice(pagoDTO.getPrecio())
                        .build();


        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder().
                notificationUrl("https://0f0c-38-51-80-25.ngrok-free.app/api/v1/pagos/recibirWebHook")
                .backUrls(PreferenceBackUrlsRequest.builder().
                        success("http://localhost:8080/api/v1/pagos/exito").
                        pending("https://0f0c-38-51-80-25.ngrok-free.app/api/v1/pagos/pendiente").
                        failure("https://0f0c-38-51-80-25.ngrok-free.app/api/v1/pagos/fallo")
                        .build())
                .items(items).
                build();
        PreferenceClient client = new PreferenceClient();


        try {
            Preference preference = client.create(preferenceRequest);

            return ResponseEntity.ok().body(preference);
        } catch (MPException e) {
            return ResponseEntity.status(500).body("Error al crear la preferencia de pago");
        } catch (MPApiException e) {
            return ResponseEntity.status(500).body("Error al crear la preferencia de pago");
        }
    }


    @GetMapping("/exito")
    public ResponseEntity<?> exitoTransaccion(
            @RequestParam("collection_id") String collectionId,
                                              @RequestParam("collection_status") String collectionStatus,
                                              @RequestParam("payment_id") String paymentId,
                                              @RequestParam("status") String status,
                                              @RequestParam("merchant_order_id") String merchantOrderId,
                                              @RequestParam("preference_id") String preferenceId) {
    		
    	return ResponseEntity.ok().body("{\"response\": \"200\", \"estatus\": \""
    	+ status + "\", \"id del pago: \": \"" + paymentId + "\"}");
    }

    @GetMapping("/pendiente")
    public ResponseEntity<?> transaccionPendiente(){
        return ResponseEntity.ok().body("pendiente");
    }

    @GetMapping("/fallo")
    public ResponseEntity<?> TransaccionFallo(@RequestParam("status") String status){
        return ResponseEntity.ok().body("{\"response\": \"400\", \"estatus\": \""
            	+ status + "\"}");
    }

    //WebHook escucha los eventos que vengan de mercado pago, por ejemplo el usuario esta pagando
    //el usaurio ya pago

    @PostMapping("/recibirWebHook")
    public ResponseEntity<?> recibirWebHook(@RequestBody PayloadDTO payloadDTO) {
        if (payloadDTO.getData() != null) {
            HistorialPago historialPago = new HistorialPago();
            historialPago.setEstado(payloadDTO.getAction());
            historialPago.setFechaPago(payloadDTO.getDate_created());
            historialPago.setIdentificadorPago(payloadDTO.getData().getId());
            paymentServices.guardarPago(historialPago);
        } else {

            System.out.println("El campo data en el payload es nulo" + payloadDTO);
        }

        return ResponseEntity.ok().body("Recibido");
    }



}
