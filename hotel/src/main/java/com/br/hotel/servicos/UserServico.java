package com.br.hotel.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.UserCliente;

@Service
public class UserServico {


    @Autowired
    private UserClienteRepositorio userClienteRepositorio;

    public UserCliente SalvarUsuario(UserCliente usuario){
        return userClienteRepositorio.save(usuario);
    }
    
}
