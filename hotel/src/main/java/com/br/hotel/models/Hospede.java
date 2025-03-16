package com.br.hotel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*Classe java de quem vai se cadastrar no aplicativo */

@Entity
public class Hospede {

    @Column(length = 11, nullable = false,unique = true)
    private String cpf;

    @Column(columnDefinition = "char(60)",nullable = false)
    private String senha;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 150, nullable = true)
    private String email;


    @Column(columnDefinition = "tinyint(1) default 1",nullable = false)
    private boolean enable;

    @Column(length = 11, nullable = false)
    private String telefone;
    
}
