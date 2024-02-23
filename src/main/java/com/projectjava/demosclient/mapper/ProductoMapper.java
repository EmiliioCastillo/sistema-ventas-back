package com.projectjava.demosclient.mapper;

import com.projectjava.demosclient.dto.ProductosDto;
import com.projectjava.demosclient.entity.Productos;

public class ProductoMapper {


    // Convert User JPA Entity into UserDto
    public static ProductosDto mapToProductDto(Productos product){
        ProductosDto productDto = new ProductosDto(
                product.getIdProductos(),
                product.getCodigo(),
                product.getDescripcion(),
                product.getCategoria(),
                product.getAlmacen()
        );
        return productDto;
    }


    // Convert UserDto into User JPA Entity
    public static Productos mapToProduct(ProductosDto productDto){
        Productos product = new Productos(
                productDto.getId(),
                productDto.getCodigo(),
                productDto.getDescripcion(),
                productDto.getCategoria(),
                productDto.getAlmacen()
        );
        return product;
    }


}
