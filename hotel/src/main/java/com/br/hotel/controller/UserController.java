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
import com.br.hotel.servicos.UserServico;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class UserController {

    @Autowired
    private UserClienteRepositorio userrepositorio;

    @Autowired
    private UserServico userServico;

    @GetMapping("/")
    public ModelAndView home(UserCliente userCliente){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("User", new UserCliente());
        mv.addObject("Usuarios", userrepositorio.findAll());
        return mv;
    }

    @PostMapping("/mainpage")
    public ModelAndView cadastrar(@Valid UserCliente usercliente, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            ModelAndView mv = new ModelAndView("cadastro");
            return mv;
        }
        if (userrepositorio.findByCpf(usercliente.getCpf()).isPresent()) {
           ModelAndView mv = new ModelAndView("error");
            return mv;
        }
        userServico.persist(usercliente);
        session.setAttribute("cpfUsuario", usercliente.getCpf());
        
        ModelAndView mv = new ModelAndView("mainpage");
        mv.addObject("userLogado", usercliente); // Passa o usuário para a tela principal
        return mv;
    }
    
}

