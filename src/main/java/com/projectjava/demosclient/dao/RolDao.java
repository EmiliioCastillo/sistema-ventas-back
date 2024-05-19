package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol,Long> {
}
