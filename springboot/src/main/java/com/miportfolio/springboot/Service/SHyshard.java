package com.miportfolio.springboot.Service;

import com.miportfolio.springboot.Entity.Hyshard;
import com.miportfolio.springboot.Repository.RHyshard;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHyshard {
    @Autowired
    RHyshard rHyshard;
    
    public List<Hyshard> list(){
        return rHyshard.findAll();
    }
    
    public Optional<Hyshard> getOne(int id){
        return rHyshard.findById(id);
    }
    
    public Optional<Hyshard> getByNombre(String nombre){
        return rHyshard.findByNombre(nombre);
    }
    
    public void save(Hyshard skill){
        rHyshard.save(skill);
    }
    
    public void delete(int id){
        rHyshard.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHyshard.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHyshard.existsByNombre(nombre);
    }
}
