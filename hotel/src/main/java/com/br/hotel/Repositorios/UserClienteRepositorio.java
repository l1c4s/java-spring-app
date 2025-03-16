package com.br.hotel.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.hotel.models.UserCliente;

@Repository
public interface UserClienteRepositorio extends JpaRepository<UserCliente, Long> {
    
}
