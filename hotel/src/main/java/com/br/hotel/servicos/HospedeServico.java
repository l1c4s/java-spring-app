package com.br.hotel.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.hotel.Repositorios.HospedeRepositorio;
import com.br.hotel.models.Hospede;

@Service
public class HospedeServico {
    
    @Autowired
    private HospedeRepositorio hospederepositorio;

    public Hospede salvarHospede(Hospede hospede){
        return hospederepositorio.save(hospede);
    }

    public List<Hospede> ListarHospedes(String UsuarioCpf){
        return hospederepositorio.findByUsercliente_Cpf(UsuarioCpf);
    }
}
