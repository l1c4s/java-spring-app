package com.br.hotel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/* classe java do usario do aplicativo */

@Setter
@Getter
@Entity
public class UserCliente {


    @Column(length = 11, nullable = false,unique = true)
    private String cpf;

    @Column(columnDefinition = "char(60)",nullable = false)
    private String senha;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(length = 50, nullable = false)
    private String username;

   

    @Column(length = 150, nullable = true)
    private String email;


    @Column(columnDefinition = "tinyint(1) default 1",nullable = false)
    private boolean enable;

    @Column(length = 11, nullable = false)
    private String telefone;
    
}

