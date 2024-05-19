package com.projectjava.demosclient.services.historialPagoService;

import com.projectjava.demosclient.dao.HistorialPagoDao;
import com.projectjava.demosclient.entity.HistorialPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistorialPagoServiceImpl implements HistorialPagoService{
    @Autowired
    HistorialPagoDao historialPagoDao;



    @Override
    public List<HistorialPago> findAll() {
        List<HistorialPago> listPagos = historialPagoDao.findAll();
        return listPagos;
    }
}
