package com.m2i.demomedical.service;

import java.util.Optional;

import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.repository.VilleRepository;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

    private VilleRepository pr;

    public VilleService(VilleRepository pr) {
        this.pr = pr;
    }

    public Iterable<VilleEntity> getList(){
        return pr.findAll();
    }

    public VilleEntity find(int id) {
        return pr.findById( id ).get();
    }

    public VilleEntity add(String nom, int cp) {
        VilleEntity p = new VilleEntity();
        p.setNom(nom);
        p.setCodePostal( cp ) ;
        pr.save( p );
    }

    public VilleEntity edit(int id, String nom, int cp) {
        VilleEntity p = pr.findById(id).get();
        p.setNom(nom);
        p.setCodePostal( cp ) ;
        pr.save( p );
        return p; 
    }

    public void delete(int id) {
        pr.deleteById(id);
    }
}

