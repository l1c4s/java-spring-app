package com.br.hotel.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.UserCliente;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class UserController {

    @Autowired
    private UserClienteRepositorio userrepositorio;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/mainpage")
    public ModelAndView cadastrar(@Valid UserCliente usercliente, BindingResult result) {
        
        if(result.hasErrors())
            return new ModelAndView("index").addObject("usercliente", new UserCliente());
        try{
            userrepositorio.save(usercliente);
        }catch(Exception e){
            result.rejectValue("cpf", "Error.user", "erro ao salvar o usuario: "+e.getMessage());
            return new ModelAndView("index").addObject("usercliente", new UserCliente());
        }
        
        return new ModelAndView("redirect:/mainpage");
    }
    
    
    
}

