package com.m2i.demomedical.api;

import java.net.URI;

import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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

    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<VilleEntity> addVilleApi(@RequestBody VilleEntity ville) {
        try{
            VilleEntity createVille = vs.add(ville.getNom() , ville.getCodePostal() ); 

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createVille.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createVille);

        }catch ( Exception e ){
            System.out.println("Je suis ici");
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
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