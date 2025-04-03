package com.br.hotel.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*Classe java de quem vai se cadastrar no aplicativo */

@Getter
@Setter
@Entity
@Table(name = "Hospedes")
public class Hospede {

    
    @Column(length = 11, nullable = false,unique = true)
    private String cpf;

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(length = 50, nullable = false)
    private String nome;


    @Column(columnDefinition = "tinyint(1) default 1",nullable = false)
    private boolean enable;

    @Column(length = 11, nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "usercliente_cpf",nullable = false)
    private UserCliente usercliente;
    
}
