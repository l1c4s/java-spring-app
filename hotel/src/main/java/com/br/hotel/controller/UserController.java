package com.br.hotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class UserController {

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/mainpage")
    public String postMethodName(@RequestBody String cpf, String senha) {
        //TODO: process POST request
        
        return "redirect:/mainpage";
    }
    
    
    
}

