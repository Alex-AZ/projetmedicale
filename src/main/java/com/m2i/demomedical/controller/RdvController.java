package com.m2i.demomedical.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.m2i.demomedical.entities.PatientEntity;
import com.m2i.demomedical.entities.RdvEntity;
import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.helpers.LoggingHelper;
import com.m2i.demomedical.repository.RdvRepository;
import com.m2i.demomedical.service.PatientService;
import com.m2i.demomedical.service.RdvService;
import com.m2i.demomedical.service.VilleService;

@Controller
@RequestMapping("/rdv")
public class RdvController {
	
	@Autowired
    final private RdvService rs;
	
	@Autowired
    final private PatientService ps;

    public RdvController(RdvService rs , PatientService ps) {
        this.rs = rs;
        this.ps = ps;
    }
    
	@GetMapping("")
    public String list( Model model ){ 
        model.addAttribute( "liste_rdv" , rs.getList() );
        model.addAttribute( "liste_patients" , ps.getList() );
        model.addAttribute( "isConsultation" , true );
        System.out.println( ps.getList() ); 
        return "rdv/list";
    }
	
	@PostMapping("/add")
    public String addPost( HttpServletRequest request, Model model  ){
        String patient = request.getParameter("patient");
        String duree = request.getParameter("duree");
        String type = request.getParameter("typerdv");
        String dateheure = request.getParameter("datev") + " " + request.getParameter("timev");
        String note = request.getParameter("note");

        try{
            rs.addRdv( patient , duree, type, dateheure, note );
            return "redirect:/rdv?success";
        }catch( Exception e ){
            model.addAttribute("error" , e.getMessage() );
            return "rdv/ajout_edition";
        }
    }
	
	@DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete( @PathVariable int id ){
        try{
            rs.delete(id);
        }catch( Exception e ){
        	throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
        return ""; 
    }
	
	@GetMapping("/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        try{
            model.addAttribute( "entete_titre" , "Editer un Rdv" );
            model.addAttribute( "liste_patients" , ps.getList() );
            RdvEntity rdv = rs.find(id); 
            model.addAttribute( "v" , rdv );
            model.addAttribute( "isConsultation" , rdv.getType() == "consultation" ); 
            return "rdv/ajout_edition";
        }catch ( Exception e ){
            return "redirect:/rdv?error="+e.getMessage();
        }
    }

    @PostMapping("/edit/{id}")
    public String editPost(  Model model , HttpServletRequest request , @PathVariable int id ){
    	String patient = request.getParameter("patient");
        String duree = request.getParameter("duree");
        String type = request.getParameter("typerdv");
        String dateheure = request.getParameter("datev") + " " + request.getParameter("timev");
        String note = request.getParameter("note");

        try{
            rs.editRdv( id ,  patient , duree, type, dateheure, note );
            return "redirect:/rdv?success";
        }catch( Exception e ){
            model.addAttribute("error" , e.getMessage() );
            return "rdv/ajout_edition";
        }
    }
    
    
}
