package com.br.hotel.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;





@Controller
public class UserController {

    @Autowired
    private UserClienteRepositorio userrepositorio;

    @Autowired
    private UserServico userServico;

    @GetMapping({"/", "/index"})
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

    try {
        if ("login".equals(action)) {
            var usuarioOptional = userrepositorio.findByCpf(cpf);

            if (usuarioOptional.isPresent()) {
                var usuario = usuarioOptional.get();

                if (new BCryptPasswordEncoder().matches(senha, usuario.getSenha())) {
                    session.setAttribute("cpfUsuario", cpf);
                    ModelAndView mv = new ModelAndView("mainpage");
                    mv.addObject("userLogado", usuario);
                    return mv;
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ou senha inválida");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ou senha inválida"); // CPF não encontrado
            }
        }

        if ("cadastrar".equals(action)) {
            if (userrepositorio.findByCpf(cpf).isPresent()) {
                return new ModelAndView("redirect:/errors?status=409"); // CPF já cadastrado
            }

            var userCliente = new UserCliente();
            userCliente.setCpf(cpf);
            userCliente.setSenha(new BCryptPasswordEncoder().encode(senha));

            if(userCliente.getCpf().length()<11){
                throw new IllegalAccessException("CPF invalido");
            }

            userServico.persist(userCliente);
            session.setAttribute("cpfUsuario", cpf);
            ModelAndView mv = new ModelAndView("mainpage");
            mv.addObject("userLogado", userCliente);
            return mv;
        }
    } catch (Exception e) {
        return new ModelAndView("redirect:/errors?status=500"); // Erro inesperado
    }

    return new ModelAndView("redirect:/errors?status=400"); // Ação inválida
}

    
}

