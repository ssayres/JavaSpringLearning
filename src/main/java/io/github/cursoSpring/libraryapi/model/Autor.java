package io.github.cursoSpring.libraryapi.model;



import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

import java.util.UUID;


@Entity

@Table(name = "autor", schema = "public")

@Getter

@Setter

@ToString

public class Autor {


    @Id

    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;


    @Column(name = "nome", length = 100, nullable= false) // atributos como length nao são necessarios pois o banco já delimita é opcional

    private String nome;


    @Column(name= "data_nascimento", nullable = false)

    private LocalDate dataNascimento;


    @Column(name = "nacionalidade", length = 50, nullable = false)

    private String nacionalidade;


    public UUID getId() {

        return id;

    }


    public void setId(UUID id) {

                this.id = id;

    }


    public String getNome() {

        return nome;

    }


    public void setNome(String nome) {

                this.nome = nome;

    }


    public LocalDate getDataNascimento() {

        return dataNascimento;

    }


    public void setDataNascimento(LocalDate dataNascimento) {

                this.dataNascimento = dataNascimento;

    }


    public String getNacionalidade() {

        return nacionalidade;

    }


    public void setNacionalidade(String nacionalidade) {

                this.nacionalidade = nacionalidade;

    }

}