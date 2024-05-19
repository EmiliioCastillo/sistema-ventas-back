package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Notificacion;
import com.projectjava.demosclient.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface NotificacionDao extends JpaRepository<Notificacion, Long> {

    boolean existsByUsuarioAndFechaNotificacion(Usuario usuario, Date fechaNotificacion);

    Optional<Notificacion> findByUsuarioAndFechaNotificacion(Usuario usuario, Date fechaNotificacion);
}
