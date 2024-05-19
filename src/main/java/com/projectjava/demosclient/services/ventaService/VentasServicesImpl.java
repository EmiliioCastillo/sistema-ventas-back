package com.projectjava.demosclient.services.ventaService;

import com.projectjava.demosclient.dao.ProductoDao;
import com.projectjava.demosclient.dao.VentasDao;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Ventas;
import jakarta.persistence.PersistenceException;
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

import java.util.*;

@Service
public class VentasServicesImpl implements VentaService{

    @Autowired
    VentasDao ventaDao;

    @Autowired
    ProductoDao productoDao;
    @Override
    public Page<Ventas> findByFechaCreacion(Date fechaCreacion, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").descending());

            Specification<Ventas> specification = (root, query, builder) -> {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();

                if (fechaCreacion != null) {
                    // Comparación entre rangos de fechas
                    Calendar startOfDay = Calendar.getInstance();
                    startOfDay.setTime(fechaCreacion);
                    startOfDay.set(Calendar.HOUR_OF_DAY, 0);
                    startOfDay.set(Calendar.MINUTE, 0);
                    startOfDay.set(Calendar.SECOND, 0);
                    startOfDay.set(Calendar.MILLISECOND, 0);

                    Calendar endOfDay = Calendar.getInstance();
                    endOfDay.setTime(fechaCreacion);
                    endOfDay.set(Calendar.HOUR_OF_DAY, 23);
                    endOfDay.set(Calendar.MINUTE, 59);
                    endOfDay.set(Calendar.SECOND, 59);
                    endOfDay.set(Calendar.MILLISECOND, 999);

                    predicates.add(builder.between(root.get("fechaCreacion"), startOfDay.getTime(), endOfDay.getTime()));
                }
                query.where(builder.and(predicates.toArray(new Predicate[0])));
                return null;
            };
            return ventaDao.findAll(specification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al consultar producto: " + e.getMessage());
            throw new RuntimeException("Error al consultar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<Ventas> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ventaDao.findAll(pageable);
    }

    @Override
    public Optional<Ventas> findById(Long id) {
       return ventaDao.findById(id);
    }




    public Map<Long, Productos> listaProductos() {
        try {
            List<Productos> listaProductos = productoDao.findAll(Sort.by(Sort.Direction.ASC, "fechaEntrega"));
            Map<Long, Productos> productosMap = new HashMap<>();

            for (Productos productos : listaProductos) {
                productosMap.put(productos.getIdProductos(), productos);
            }
            return productosMap;

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public ResponseEntity<String> save(Ventas venta) {
        try {
            if (ventaDao.existsByIdVentas(venta.getIdVentas())) {

                return ResponseEntity.ok("{\"response\": \"Duplicado\"}");
            }
            ventaDao.save(venta);
            return ResponseEntity.ok("{\"response\": \"200\"}");


        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }
    }

    @Override
    public Ventas saveVenta(Ventas venta) {

             return ventaDao.save(venta);
    }

    @Override
    public void deleteById(Long id) {
        ventaDao.deleteById(id);
    }
}
