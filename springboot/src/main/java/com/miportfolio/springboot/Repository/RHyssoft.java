package com.miportfolio.springboot.Repository;

import com.miportfolio.springboot.Entity.Hyssoft;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHyssoft extends JpaRepository<Hyssoft, Integer>{
    Optional<Hyssoft> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

