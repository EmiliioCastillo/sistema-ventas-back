package com.projectjava.demosclient.services.productoService;
import com.projectjava.demosclient.dto.ProductosDTO;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public interface ProductoServices{



    Page<Productos> findByCategoriaAndCodigoAndDescripcionAndNombre
            (String categoria, String codigo, String descripcion, Date fechaEntrega, String nombre, int page, int size);
    boolean existsByCodigo(Productos productos);

    public ResponseEntity<String> save(Productos producto);


    Map<Long, Proveedor> listaProveedores();

    public void deleteById(Long id);

    Productos saveProductoVenta(Productos producto);
    public Optional<Productos> findById(Long id);

    public ResponseEntity<String> saveProductsToExcel(MultipartFile file);



}
