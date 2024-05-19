package com.projectjava.demosclient.services.gastosService;

import com.projectjava.demosclient.dao.GastosDao;
import com.projectjava.demosclient.dto.GastosProveedorDTO;
import com.projectjava.demosclient.entity.Gastos;
import com.projectjava.demosclient.services.authService.AuthService;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GastosServiceImpl implements GastosService{

    @Autowired
    GastosDao gastoDao;
    @Autowired
    AuthService authService;

    @Override
    public Page<Gastos> findByCategoriaAndFechaPago(String categoria, Date fechaPago, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("fechaPago").descending());

            Specification<Gastos> specification = (root, query, builder) -> {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();
                if(categoria != null){
                    predicates.add(builder.equal(root.get("categoria"), categoria));
                }
                if (fechaPago != null) {
                    // Comparación entre rangos de fechas
                    Calendar startOfDay = Calendar.getInstance();
                    startOfDay.setTime(fechaPago);
                    startOfDay.set(Calendar.HOUR_OF_DAY, 0);
                    startOfDay.set(Calendar.MINUTE, 0);
                    startOfDay.set(Calendar.SECOND, 0);
                    startOfDay.set(Calendar.MILLISECOND, 0);

                    Calendar endOfDay = Calendar.getInstance();
                    endOfDay.setTime(fechaPago);
                    endOfDay.set(Calendar.HOUR_OF_DAY, 23);
                    endOfDay.set(Calendar.MINUTE, 59);
                    endOfDay.set(Calendar.SECOND, 59);
                    endOfDay.set(Calendar.MILLISECOND, 999);

                    predicates.add(builder.between(root.get("fechaPago"), startOfDay.getTime(), endOfDay.getTime()));
                }
                query.where(builder.and(predicates.toArray(new Predicate[0])));
                return null;
            };
            return gastoDao.findAll(specification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al consultar el gasto: " + e.getMessage());
            throw new RuntimeException("Error al consultar gasto: " + e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<String> save(Gastos gasto) {
        try {
            // Obtener el usuario logueado
            String usuarioLogueado = authService.obtenerUsuarioLogueado().getEmail();

            // Asignar el usuario logueado al gasto
            gasto.setUsuarioCreacion(usuarioLogueado);
            // Guardar el gasto en la base de datos
            gastoDao.save(gasto);
            return ResponseEntity.ok("{\"response\": \"200\"}");

        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }
    }

    @Override
    public ResponseEntity<String> edit(Gastos gasto) {
            try {
                gastoDao.save(gasto);
                return ResponseEntity.ok("{\"response\": \"200\"}");

            } catch (HibernateException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

            } catch (PersistenceException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

            }
        }

    @Override
    public Set<String> findAllCategorias() {
        List<Gastos> listaCategorias = gastoDao.findAll(Sort.by(Sort.Direction.ASC, "categoria"));
        Set<String> listCategorias = new HashSet<>();
        for (Gastos categorias : listaCategorias) {
            listCategorias.add(categorias.getCategoria());
        }
        return listCategorias;
    }

    @Transactional(readOnly = false) // ReadOnly debe ser true si no haces modificaciones en la base de datos
    @Override
    public Page<GastosProveedorDTO> consultarGastoYProveedor(String nombre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Object[]> resultList = gastoDao.consultarGastoYProveedor();
        List<GastosProveedorDTO> dtoList = new ArrayList<>();

        for (Object[] result : resultList) {
            GastosProveedorDTO dto = new GastosProveedorDTO();
            dto.setNombreProveedor((String) result[0]);
            dto.setImporte((double) result[1]);
            dto.setMedioPago((String) result[2]);
            dtoList.add(dto);
        }

        List<GastosProveedorDTO> gastosFiltrados = new ArrayList<>();
        for (GastosProveedorDTO gasto : dtoList) {
            if (nombre == null || nombre.equals(gasto.getNombreProveedor())) {
                gastosFiltrados.add(gasto);
            }
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), gastosFiltrados.size());
        Page<GastosProveedorDTO> pageResult = new PageImpl<>(gastosFiltrados.subList(start, end), pageable, gastosFiltrados.size());
        return pageResult;
    }


    @Override
    public Optional<Gastos> findById(Long id) {
        return gastoDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        gastoDao.deleteById(id);
    }
}
