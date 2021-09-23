package com.m2i.demomedical.service;

import com.m2i.demomedical.entities.PatientEntity;
import com.m2i.demomedical.entities.RdvEntity;
import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.repository.RdvRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdvService {

	@Autowired
    private RdvRepository rr;

	
	public Iterable<RdvEntity> getList(){
        return rr.findAll();
    }
	
	public RdvRepository getRr() {
		return rr;
	}

	public void setRr(RdvRepository rr) {
		this.rr = rr;
	}
	
	public List<Object> getRdvStats() {
		return rr.getRdvStats(); 
	}


	public RdvEntity addRdv(String patient, String duree, String type, String dateheure, String note) throws ParseException {
		RdvEntity r = new RdvEntity();
        r.setDuree( Integer.parseInt(duree) );
        r.setType( type );
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        Date date = formatter.parse(dateheure);
        
        r.setDateheure( date );
        r.setNote(note);
        PatientEntity pEntity = new PatientEntity();
        pEntity.setId( Integer.parseInt( patient ) ); 
        r.setPatient(pEntity);
        rr.save( r );
        return r;
	}
	
	public RdvEntity editRdv( int id,  String patient, String duree, String type, String dateheure, String note) throws ParseException {
		RdvEntity r = rr.findById(id).get(); 
        r.setDuree( Integer.parseInt(duree) );
        r.setType( type );
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        Date date = formatter.parse(dateheure);
        
        r.setDateheure( date );
        r.setNote(note);
        PatientEntity pEntity = new PatientEntity();
        pEntity.setId( Integer.parseInt( patient ) ); 
        r.setPatient(pEntity);
        rr.save( r );
        return r;
	}
	
	
	public void delete( int id ) {
		rr.deleteById(id);
	}
	
    public RdvEntity find(int id) {
        return rr.findById( id ).get();
    }
}

