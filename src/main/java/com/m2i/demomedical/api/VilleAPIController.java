package com.m2i.demomedical.api;

import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ville")
public class VilleAPIController {
    @Autowired
    VilleService vs;

    @GetMapping(path = "", produces = "application/json")
    Iterable<VilleEntity> getAllVilleApi() {
        return vs.getList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    VilleEntity getVilleByIdApi(@PathVariable(name = "id") String id) {
        return vs.find(Integer.parseInt(id));
    }

    @PostMapping(path = "/add", produces = "application/json")
    VilleEntity addVilleApi(@RequestBody VilleEntity ville) {
        return vs.add(ville.getNom() , ville.getCodePostal() );
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    VilleEntity updateVilleApi(@PathVariable(name = "id") int id, @RequestBody VilleEntity ville ) {
        return vs.edit(id, ville.getNom(), ville.getCodePostal());
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteVille(@PathVariable(name = "id") int id) {
        vs.delete(id);
    }
}