package com.br.hotel.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hotel.models.Hospede;

public interface HospedeRepositorio extends JpaRepository<Hospede,Long> {

    List<Hospede> findByUsercliente_Cpf(String Cpf);
    
}
