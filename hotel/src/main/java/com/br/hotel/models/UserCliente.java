package com.br.hotel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/* classe java do usario do aplicativo */

@Setter
@Getter
@Entity
@Table(name = "Usuario")
public class UserCliente {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String senha;
    
    
}

