package com.m2i.demomedical.api;

import com.m2i.demomedical.entities.PatientEntity;
import com.m2i.demomedical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/ws/patient")
public class PatientAPIController {

    @Autowired
    PatientService ps;

    @GetMapping(path="", produces = "application/json")
    List<PatientEntity> all() {
        return (List<PatientEntity>) ps.getList();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    PatientEntity get( @PathVariable("id") int id ) {
        return ps.find(id);
    }

    @PostMapping(path="", produces = "application/json")
    PatientEntity postme( @RequestBody PatientEntity patient ) throws Exception {
        return ps.addPatient( patient.getNom() , patient.getPrenom() , patient.getTelephone() , patient.getEmail() , patient.getVille().getId() );
    }

}
