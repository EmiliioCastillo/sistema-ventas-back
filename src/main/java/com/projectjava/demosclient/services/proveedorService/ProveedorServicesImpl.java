package com.projectjava.demosclient.services.proveedorService;


import com.projectjava.demosclient.dao.ProveedorDao;
import com.projectjava.demosclient.dto.ProductosDTO;
import com.projectjava.demosclient.dto.ProveedorDTO;
import com.projectjava.demosclient.entity.Proveedor;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class ProveedorServicesImpl implements ProveedorServices {

    @Autowired
    private ProveedorDao proveedorDao;

    @Transactional(readOnly = false)
    @Override
    public Page<ProveedorDTO> findAll(String nombre, String estatus, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Object[]> resultados = proveedorDao.buscarTodosProveedores();

        List<ProveedorDTO> proveedores = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ProveedorDTO proveedor = new ProveedorDTO();
            proveedor.setIdProveedor((Long) resultado[0]);
            proveedor.setNombre((String) resultado[1]);
            proveedor.setEmail((String) resultado[2]);
            proveedor.setTelefono((String) resultado[3]);
            proveedor.setDireccion((String) resultado[4]);
            proveedor.setNumeroTributario((String) resultado[5]);
            proveedor.setEstatus((String) resultado[6]);

            proveedores.add(proveedor);
        }

        if (nombre != null || estatus != null) {
            List<ProveedorDTO> proveedoresFiltrados = new ArrayList<>();
            for (ProveedorDTO proveedor : proveedores) {
                boolean nombreMatch = nombre == null || nombre.equals(proveedor.getNombre());
                boolean estatusMatch = estatus == null || estatus.equals(proveedor.getEstatus());
                if (nombreMatch && estatusMatch) {
                    proveedoresFiltrados.add(proveedor);
                }
            }
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), proveedoresFiltrados.size());
            return new PageImpl<>(proveedoresFiltrados.subList(start, end), pageable, proveedoresFiltrados.size());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), proveedores.size());
        return new PageImpl<>(proveedores.subList(start, end), pageable, proveedores.size());
    }

    @Transactional(readOnly = false)
    @Override
    public ResponseEntity<String> save(ProveedorDTO proveedor) {
        try {
            String regexNombre = "[a-zA-ZáéíóúñÑ]+";
            String numeroTelefono = "^\\+?[0-9]{10,13}$";
            if (proveedor.getNombre().matches(regexNombre)
                    && proveedor.getTelefono().matches(numeroTelefono)) {
                proveedorDao.insertarProveedores(proveedor.getNombre(),
                        proveedor.getDireccion(),
                        proveedor.getEmail(),
                        proveedor.getTelefono(),
                        proveedor.getEstatus(),
                        proveedor.getNumeroTributario());
                return ResponseEntity.ok("{\"response\": \"200\"}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"response\": \"not-valid\"}");
            }
        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la Entidad");
        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la Entidad");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public ResponseEntity<String> actualizarProveedor(ProveedorDTO proveedor) {
        try {
            String regexNombre = "[a-zA-ZáéíóúñÑ]+";
            String numeroTelefono = "^\\+?[0-9]{10,13}$";
            if(proveedor.getNombre().matches(regexNombre)
                    && proveedor.getTelefono().matches(numeroTelefono)){
                proveedorDao.actualizarProveedor(proveedor.getIdProveedor(),
                        proveedor.getNombre(),
                        proveedor.getDireccion(), proveedor.getEmail(),
                        proveedor.getTelefono(), proveedor.getEstatus(),
                        proveedor.getNumeroTributario());
                return ResponseEntity.ok("{\"response\": \"200\"}");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre o número de teléfono del proveedor no son válidos.");
            }
        }catch (HibernateException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la Entidad");
        }catch (PersistenceException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la Entidad");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la Entidad");
    }
    @Override
    public void deleteById(Long id) {
        proveedorDao.deleteById(id);
    }


    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorDao.findById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<ProveedorDTO> buscarProveedoresPorId(Long id) {
        List<Object[]> resultados = proveedorDao.buscarProveedorPorId(id);

        for (Object[] resultado : resultados) {
            Long idProveedor = (Long) resultado[0]; // Supongo que el primer índice contiene el ID del proveedor
            if (idProveedor.equals(id)) {
                ProveedorDTO proveedor = new ProveedorDTO();
                proveedor.setIdProveedor(idProveedor);
                proveedor.setNombre((String) resultado[1]);
                proveedor.setEmail((String) resultado[2]);
                proveedor.setDireccion((String) resultado[3]);
                proveedor.setTelefono((String) resultado[4]);
                proveedor.setNumeroTributario((String) resultado[5]);
                proveedor.setEstatus((String) resultado[6]);

                return Optional.of(proveedor);
            }
        }
        return Optional.empty(); // Devuelve un Optional vacío si no se encontró el proveedor con el ID especificado
    }

    @Override
    public Set<String> findAllNombreProveedores() {
        List<Proveedor> listaNombresProveedores = proveedorDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        Set<String> listaProveedores = new HashSet<>();
        for (Proveedor proveedor : listaNombresProveedores) {
            listaProveedores.add(proveedor.getNombre());
        }
        return listaProveedores;
    }

    @Transactional(readOnly = false)
    public Set<ProveedorDTO> listarProveedoresConProductos(String nombre, String producto, String estatus,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Object[]> resultados = proveedorDao.devolverProveedorProducto();

        Set<ProveedorDTO> proveedoresDTO = new HashSet<>();

        for (Object[] resultado : resultados) {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            proveedorDTO.setIdProveedor((Long) resultado[0]);
            proveedorDTO.setNombre((String) resultado[1]);

            // Crea una nueva lista de productos para cada proveedor
            List<ProductosDTO> productosDTOS = new ArrayList<>();

            ProductosDTO productoDTO = new ProductosDTO();
            productoDTO.setIdProductos((Long) resultado[2]);
            productoDTO.setCodigo((String) resultado[3]);
            productoDTO.setDescripcion((String) resultado[4]);
            productoDTO.setCategoria((String) resultado[5]);
            productoDTO.setProducto((String) resultado[6]);
            productoDTO.setPrecio((String) resultado[7]);
            productoDTO.setCantidad((String) resultado[8]);
            productoDTO.setEstatus((String) resultado[9]);
            productoDTO.setFechaEntrega((Date) resultado[10]);

            productosDTOS.add(productoDTO);

            proveedorDTO.setProductosList(productosDTOS);

            // Agregar el proveedor a la lista de proveedores
            proveedoresDTO.add(proveedorDTO);
        }

        // Filtrar los proveedores por nombre si se proporciona el parámetro
        if (nombre != null) {
            List<ProveedorDTO> proveedoresFiltrados = new ArrayList<>();
            for (ProveedorDTO proveedor : proveedoresDTO) {
                if (nombre.equals(proveedor.getNombre())) {
                    proveedoresFiltrados.add(proveedor);
                }
            }
            return new HashSet<>(proveedoresFiltrados);
        }
        // Filtrar los proveedores por producto si se proporciona el parámetro
        if(producto != null){
            List<ProveedorDTO> proveedoresFiltrados = new ArrayList<>();
            for (ProveedorDTO proveedor : proveedoresDTO) {
                for (ProductosDTO productoProveedor : proveedor.getProductosList()) {
                    if (producto.equals(productoProveedor.getProducto())) {
                        proveedoresFiltrados.add(proveedor);
                        break; // Salir del bucle interno si se encuentra un producto coincidente
                    }
                }
            }
            return new HashSet<>(proveedoresFiltrados);
        }
        // Filtrar los proveedores por estatus si se proporciona el parámetro
        if(estatus != null){
            List<ProveedorDTO> proveedoresFiltrados = new ArrayList<>();
            for (ProveedorDTO proveedor : proveedoresDTO) {
                for (ProductosDTO productoProveedor : proveedor.getProductosList()) {
                    if (estatus.equals(productoProveedor.getEstatus())) {
                        proveedoresFiltrados.add(proveedor);
                        break; // Salir del bucle interno si se encuentra un estatus coincidente
                    }
                }
            }
            return new HashSet<>(proveedoresFiltrados);
        }

        return proveedoresDTO;
    }



}

