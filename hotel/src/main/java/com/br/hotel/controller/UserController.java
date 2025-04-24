package com.br.hotel.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @PostMapping("/")
public ModelAndView processarForm(@RequestParam String cpf, 
                                  @RequestParam String senha, 
                                  @RequestParam String action, 
                                  HttpSession session) {
    
    // Lógica de Login
    if ("login".equals(action)) {
        // Verifica se o CPF existe
        Optional<UserCliente> usuarioOptional = userrepositorio.findByCpf(cpf);
        
        if (usuarioOptional.isPresent()) {
            UserCliente usuario = usuarioOptional.get();
            
            // Verifica a senha
            if (new BCryptPasswordEncoder().matches(senha, usuario.getSenha())) {
                session.setAttribute("cpfUsuario", cpf);
                ModelAndView mv = new ModelAndView("mainpage");
                mv.addObject("userLogado", usuario);
                return mv;
            } else {
                ModelAndView mv = new ModelAndView("index");
                mv.addObject("error", "Senha inválida.");
                return mv;
            }
        } else {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", "CPF não encontrado.");
            return mv;
        }
    }

    // Lógica de Cadastro
    if ("cadastrar".equals(action)) {
        UserCliente userCliente = new UserCliente();
        userCliente.setCpf(cpf);
        userCliente.setSenha(new BCryptPasswordEncoder().encode(senha)); // Criptografa a senha

        if (userrepositorio.findByCpf(cpf).isPresent()) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", "Este CPF já está cadastrado.");
            return mv;
        }

        userServico.persist(userCliente);
        session.setAttribute("cpfUsuario", cpf);
        ModelAndView mv = new ModelAndView("mainpage");
        mv.addObject("userLogado", userCliente);
        return mv;
    }

    return new ModelAndView("index");
}

    
}

