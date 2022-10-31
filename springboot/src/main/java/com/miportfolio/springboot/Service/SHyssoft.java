package com.miportfolio.springboot.Service;

import com.miportfolio.springboot.Entity.Hyssoft;
import com.miportfolio.springboot.Repository.RHyssoft;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHyssoft {
    @Autowired
    RHyssoft rHyssoft;
    
    public List<Hyssoft> list(){
        return rHyssoft.findAll();
    }
    
    public Optional<Hyssoft> getOne(int id){
        return rHyssoft.findById(id);
    }
    
    public Optional<Hyssoft> getByNombre(String nombre){
        return rHyssoft.findByNombre(nombre);
    }
    
    public void save(Hyssoft skill){
        rHyssoft.save(skill);
    }
    
    public void delete(int id){
        rHyssoft.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHyssoft.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHyssoft.existsByNombre(nombre);
    }
}
