package com.br.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.hotel.Repositorios.UserClienteRepositorio;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class HomeController {

    @GetMapping("/mainpage")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("mainpage");
        return mv;
    }
    
    
}

