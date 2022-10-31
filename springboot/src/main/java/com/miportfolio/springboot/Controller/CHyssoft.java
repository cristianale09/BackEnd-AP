
package com.miportfolio.springboot.Controller;

import com.miportfolio.springboot.Dto.dtoHyssoft;
import com.miportfolio.springboot.Entity.Hyssoft;
import com.miportfolio.springboot.Security.Controller.Mensaje;
import com.miportfolio.springboot.Service.SHyssoft;
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
@RequestMapping("/softskills")
public class CHyssoft {
    @Autowired
    SHyssoft sHyssoft;

    @GetMapping("/lista")
    public ResponseEntity<List<Hyssoft>> list() {
        List<Hyssoft> list = sHyssoft.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hyssoft> getById(@PathVariable("id") int id) {
        if (!sHyssoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Hyssoft hyssoft = sHyssoft.getOne(id).get();
        return new ResponseEntity(hyssoft, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHyssoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHyssoft.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyssoft dtohyssoft) {
        if (StringUtils.isBlank(dtohyssoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sHyssoft.existsByNombre(dtohyssoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Hyssoft hyssoft = new Hyssoft(dtohyssoft.getNombre(), dtohyssoft.getPorcentaje());
        sHyssoft.save(hyssoft);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyssoft dtohyssoft) {
        //Validamos si existe el ID
        if (!sHyssoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (sHyssoft.existsByNombre(dtohyssoft.getNombre()) && sHyssoft.getByNombre(dtohyssoft.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohyssoft.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hyssoft hyssoft = sHyssoft.getOne(id).get();
        hyssoft.setNombre(dtohyssoft.getNombre());
        hyssoft.setPorcentaje(dtohyssoft.getPorcentaje());

        sHyssoft.save(hyssoft);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }
    
}

