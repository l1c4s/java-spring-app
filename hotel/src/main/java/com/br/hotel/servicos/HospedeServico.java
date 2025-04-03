package com.br.hotel.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.hotel.Repositorios.HospedeRepositorio;
import com.br.hotel.Repositorios.UserClienteRepositorio;
import com.br.hotel.models.Hospede;
import com.br.hotel.models.UserCliente;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HospedeServico {
    
    @Autowired
    private HospedeRepositorio hospederepositorio;

    @Autowired
    private UserClienteRepositorio userClienteRepositorio;
   
    public List<Hospede> ListarHospedes(){
        return hospederepositorio.findAll();
    }

    @Transactional
    public Hospede persist(Hospede hospede, String cpfUser) {
    UserCliente usuario = userClienteRepositorio.findByCpf(cpfUser)
                            .orElseThrow(()-> new RuntimeException("Usuario nao encontrrado"));

    hospede.setUsercliente(usuario);
    //usuario.getHospedes().add(hospede);
    return hospederepositorio.save(hospede);
}

    
    
}
