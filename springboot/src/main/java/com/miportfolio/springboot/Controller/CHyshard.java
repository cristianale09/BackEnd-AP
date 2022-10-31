
package com.miportfolio.springboot.Controller;

import com.miportfolio.springboot.Dto.dtoHyshard;
import com.miportfolio.springboot.Entity.Hyshard;
import com.miportfolio.springboot.Security.Controller.Mensaje;
import com.miportfolio.springboot.Service.SHyshard;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontendmiportfolio.web.app","http://localhost:4200"})
@RequestMapping("/hardskills")
public class CHyshard {
    @Autowired
    SHyshard sHyshard;

    @GetMapping("/lista")
    public ResponseEntity<List<Hyshard>> list() {
        List<Hyshard> list = sHyshard.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hyshard> getById(@PathVariable("id") int id) {
        if (!sHyshard.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Hyshard hyssoft = sHyshard.getOne(id).get();
        return new ResponseEntity(hyssoft, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHyshard.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHyshard.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyshard dtohyssoft) {
        if (StringUtils.isBlank(dtohyssoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sHyshard.existsByNombre(dtohyssoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Hyshard hyssoft = new Hyshard(dtohyssoft.getNombre(), dtohyssoft.getPorcentaje());
        sHyshard.save(hyssoft);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyshard dtohyshard) {
        //Validamos si existe el ID
        if (!sHyshard.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (sHyshard.existsByNombre(dtohyshard.getNombre()) && sHyshard.getByNombre(dtohyshard.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohyshard.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hyshard hyssoft = sHyshard.getOne(id).get();
        hyssoft.setNombre(dtohyshard.getNombre());
        hyssoft.setPorcentaje(dtohyshard.getPorcentaje());

        sHyshard.save(hyssoft);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }
    
}


