package com.m2i.demomedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.m2i.demomedical.service.RdvService;

@Controller
@RequestMapping("")
public class DashboardController {

	@Autowired
    private RdvService rs;
	
    @RequestMapping("")
    public String dashboad( Model model ){
    	
    	List<Object> data = rs.getRdvStats(); 
    	
    	System.out.println( data ); 
    	model.addAttribute("data", data); 
        return "dashboard/index";
    }

	public RdvService getRs() {
		return rs;
	}

	public void setRs(RdvService rs) {
		this.rs = rs;
	}


}