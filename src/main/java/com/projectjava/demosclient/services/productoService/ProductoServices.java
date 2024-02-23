package com.projectjava.demosclient.services.productoService;
import com.projectjava.demosclient.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoServices{

    Page<Productos> findByCategoriaAndCodigoAndDescripcion
            (String categoria, String codigo, String descripcion, int page, int size);

    boolean existsByCodigo(Productos productos);

    public void save(Productos producto);

    public void deleteById(Long id);

    public Optional<Productos> findById(Long id);

    //List<Productos> importExcel(MultipartFile file) throws Exception;
}
