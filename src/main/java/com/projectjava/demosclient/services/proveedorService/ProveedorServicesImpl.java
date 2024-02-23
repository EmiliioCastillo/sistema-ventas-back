package com.projectjava.demosclient.services.proveedorService;


import com.projectjava.demosclient.dao.ProveedorDao;
import com.projectjava.demosclient.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProveedorServicesImpl implements ProveedorServices {

    @Autowired
    private ProveedorDao proveedorDao;

    @Override
    public List<Proveedor> findAll() {
        return proveedorDao.findAll();
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
      return proveedorDao.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedorDao.deleteById(id);
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
    return proveedorDao.findById(id);

}
}
