package com.m2i.demomedical.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {

    @RequestMapping("")
    public String login(){
        return "dashboard/index";
    }


}