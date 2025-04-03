package com.br.hotel.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @OneToMany
    private List<Hospede> hospedes;
    
    
}

