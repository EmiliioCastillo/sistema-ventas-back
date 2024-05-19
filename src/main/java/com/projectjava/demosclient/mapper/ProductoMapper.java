package com.projectjava.demosclient.mapper;


import com.projectjava.demosclient.dto.ProductosDTO;
import com.projectjava.demosclient.entity.Productos;

public class ProductoMapper {


    // Convertir la entidad a dto para transferir datos
    public static ProductosDTO mapToProductDto(Productos product){
        ProductosDTO productDto = new ProductosDTO(
                product.getIdProductos(),
                product.getCodigo(),
                product.getDescripcion(),
                product.getCategoria(),
                product.getProducto(),
                product.getPrecio(),
                product.getCantidad(),
                product.getEstatus(),
                product.getFechaEntrega(),
                product.getProveedor()
        );
        return productDto;
    }


    //Convertir el dto a entity
    public static Productos mapToProduct(ProductosDTO productDto){
        Productos product = new Productos(
                productDto.getIdProductos(),
                productDto.getCodigo(),
                productDto.getDescripcion(),
                productDto.getCategoria(),
                productDto.getProducto(),
                productDto.getPrecio(),
                productDto.getCantidad(),
                productDto.getEstatus(),
                productDto.getFechaEntrega(),
                productDto.getProveedor()
        );
        return product;
    }


}
