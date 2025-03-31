package com.br.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.hotel.Repositorios.HospedeRepositorio;
import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.Hospede;
import com.br.hotel.models.UserCliente;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HospedeController {

    @Autowired
    private HospedeRepositorio hospederepositorio;

    @Autowired
    private UserClienteRepositorio userclientrepositorio;



    @GetMapping("/mainpage")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("mainpage");
        return mv;
    }

    
    

    @PostMapping("/cadastro")
    public ModelAndView cadastrarHospede(@Valid Hospede hospede, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("mainpage").addObject("Hospede", new Hospede());
        }

        if (hospede.getUsercliente().getSenha() == null || hospede.getUsercliente().getCpf() == null) {
            result.rejectValue("usercliente_cpf", "error.hospede", "CPF do usuário não encontrado.");
            return new ModelAndView("mainpage").addObject("hospede", hospede);
        }

        Optional<UserCliente> userClienteOptional = userclientrepositorio.findById(hospede.getUsercliente().getCpf());

        hospede.setUsercliente(userClienteOptional.get());

        
        try{
            hospederepositorio.save(hospede);
        }catch(Exception e){
            result.rejectValue("cpf","telefone","nome"+ e.getMessage());
            return new ModelAndView("mainpage").addObject("Hospede", new Hospede());
        }
        return new ModelAndView("redirect:cadastro");
    }
    
    
    
}
