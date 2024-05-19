package com.projectjava.demosclient.services.productoService;

import com.projectjava.demosclient.dao.ProductoDao;
import com.projectjava.demosclient.dao.ProveedorDao;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Proveedor;
import com.projectjava.demosclient.services.excelServices.ExcelUploadService;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
@Service
public class ProductoServicesImpl implements ProductoServices {
    @Autowired
    ProductoDao productoDao;

    @Autowired
    ProveedorDao proveedorDao;


    @Override
    public Page<Productos> findByCategoriaAndCodigoAndDescripcionAndNombre(String categoria, String codigo, String descripcion, Date fechaEntrega, String nombre, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("fechaEntrega").descending());

            Specification<Productos> specification = (root, query, builder) -> {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();

                if (categoria != null) {
                    predicates.add(builder.equal(root.get("categoria"), categoria));
                }
                if (codigo != null) {
                    predicates.add(builder.equal(root.get("codigo"), codigo));
                }
                if (descripcion != null) {
                    predicates.add(builder.equal(root.get("descripcion"), descripcion));
                }
                if (fechaEntrega != null) {
                    predicates.add(builder.equal(root.get("fechaEntrega"), fechaEntrega));
                }
                if (nombre != null) {
                    Join<Productos, Proveedor> proveedorJoin = root.join("proveedor");
                    predicates.add(builder.equal(proveedorJoin.get("nombre"), nombre));
                }

                // Aplica las condiciones al query usando query.where(...)
                query.where(builder.and(predicates.toArray(new Predicate[0])));

                return null; // No necesitas devolver nada aquí
            };

            return productoDao.findAll(specification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al consultar producto: " + e.getMessage());
            throw new RuntimeException("Error al consultar producto: " + e.getMessage(), e);
        }
    }



    @Override
    public ResponseEntity<String> save(Productos producto) {
        try {
            if (productoDao.existsByCodigo(producto.getCodigo())) {
                return ResponseEntity.ok("{\"response\": \"Duplicado\"}");
            }
            productoDao.save(producto);
            return ResponseEntity.ok("{\"response\": \"200\"}");


        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }
    }

    @Override
    public Map<Long, Proveedor> listaProveedores() {
        try {
            List<Proveedor> listaProveedores = proveedorDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
            Map<Long, Proveedor> proveedoresMap = new HashMap<>();

            for (Proveedor proveedor : listaProveedores) {
                proveedoresMap.put(proveedor.getIdProveedor(), proveedor);
            }
            return proveedoresMap;

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean existsByCodigo(Productos productos) {
        try {
            return productoDao.existsByCodigo(productos.getCodigo());
        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (PersistenceException e) {
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

    @Override
    public Productos saveProductoVenta(Productos producto) {
        return productoDao.save(producto);
    }

    @Override
    public ResponseEntity<String> saveProductsToExcel(MultipartFile file) {
        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                HashSet<Productos> listProductos = ExcelUploadService.getProductsFromExcel(file.getInputStream());
                boolean allProductsValid = true;
                for (Productos producto : listProductos) {
                    if (productoDao.existsByCodigo(producto.getCodigo())) {

                        allProductsValid = false;
                        return ResponseEntity.ok("{\"response\": \"Duplicado\"}");

                    }
                }
                if (allProductsValid) {
                    productoDao.saveAll(listProductos); // Solo guardar si todos los productos son válidos
                    return ResponseEntity.ok("{\"response\": \"200\"}");
                } else {
                    System.out.println("No se guardó ningún producto debido a que uno o más productos ya existen en la base de datos.");
                }
            } catch (HibernateException | IOException e) {
                throw new IllegalArgumentException("Archivo no válido");
            }
        } else {
            throw new IllegalArgumentException("Archivo no válido");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto");
    }
}


