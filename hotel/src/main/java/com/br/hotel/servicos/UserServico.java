package com.br.hotel.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.Hospede;
import com.br.hotel.models.UserCliente;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServico {


    @Autowired
    private UserClienteRepositorio userClienteRepositorio;

    public UserCliente findOrFail(String cpf){
        return userClienteRepositorio.findByCpf(cpf)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Cpf nao encontrado "+cpf)));
    }

    public Optional<UserCliente> findByCpf(String cpf){
        return userClienteRepositorio.findByCpf(cpf);
    }

    @Transactional
    public UserCliente persist(UserCliente user){
        Optional<UserCliente> existUser = userClienteRepositorio.findByCpf(user.getCpf());
        if(existUser.isPresent() && existUser.get().getCpf()!=user.getCpf()){
            throw new IllegalArgumentException("Cpf ja cadastrado");
        }return userClienteRepositorio.save(user);
    }

    @Transactional
    public void remove(Hospede hospede){

    }
    
}
