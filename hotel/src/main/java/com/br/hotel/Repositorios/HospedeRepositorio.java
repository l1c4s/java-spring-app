package com.br.hotel.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.hotel.models.Hospede;

@Repository
public interface HospedeRepositorio extends JpaRepository<Hospede,Long> {


    
    List<Hospede> findByUsercliente_Cpf(String Cpf);

    


    
}
