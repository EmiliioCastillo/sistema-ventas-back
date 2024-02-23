package com.projectjava.demosclient.services.productoService;

import com.projectjava.demosclient.dao.ProductoDao;
import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Productos;

import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicesImpl implements ProductoServices {
    @Autowired
    ProductoDao productoDao;


    @Override
    public Page<Productos> findByCategoriaAndCodigoAndDescripcion(String categoria, String codigo, String descripcion, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (categoria != null || codigo != null  || descripcion != null) {
            Specification<Productos> spec = Specification.where(null);

            if (categoria != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("categoria")), "%" + categoria.toLowerCase() + "%"));
            }

            if (codigo != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("codigo")), "%" + codigo.toLowerCase() + "%"));
            }
            if (descripcion != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"));
            }
            return productoDao.findAll(spec, pageable);
        } else {
            return productoDao.findAll(pageable);
        }
    }

    @Override
    @Transactional
     public void save(Productos producto) {

        try {
            if (existsByCodigo(producto)) {
                System.out.println("el producto ya existe");
            } else {
                productoDao.save(producto);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }catch (PersistenceException e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean existsByCodigo(Productos productos) {
        try {
            return productoDao.existsByCodigo(productos.getCodigo());
        } catch (HibernateException e) {
            e.printStackTrace();

        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        productoDao.deleteById(id);
    }

    @Override
    public Optional<Productos> findById(Long id) {
        return productoDao.findById(id);
    }



    }
