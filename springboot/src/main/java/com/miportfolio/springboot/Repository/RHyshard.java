package com.miportfolio.springboot.Repository;

import com.miportfolio.springboot.Entity.Hyshard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHyshard extends JpaRepository<Hyshard, Integer>{
    Optional<Hyshard> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

