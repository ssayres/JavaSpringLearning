package io.github.cursoSpring.libraryapi.model;



import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

import java.util.List;
import java.util.UUID;


@Entity

@Table(name = "autor", schema = "public")

@Getter

@Setter

@ToString(exclude = "livros")

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


    @OneToMany(mappedBy = "autor")
    //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
        //@Transient // uma porpriedade que não é considerada uma coluna, para ignorar a propriedade livros por enquanto
    private List<Livro> livros;


    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

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