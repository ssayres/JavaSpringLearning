package io.github.cursoSpring.libraryapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
public class Autor {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Getter
    @Column(name = "nome", length = 100, nullable= false) // atributos como length nao são necessarios pois o banco já delimita é opcional
    private String nome;

    @Setter
    @Getter
    @Column(name= "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Setter
    @Getter
    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor") // dentro da entidade livro - a relação é através da propriedade autor
    private List<Livro> livros;


}
