package io.github.cursoSpring.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="isbn", length= 20, nullable = false)
    private String isbn;

    @Column(name="titulo", length= 20, nullable = false)
    private String titulo;

    @Column(name="data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name="genero", length= 30, nullable = false)
    private GeneroLivro genero;

    @Column(name= "preco", precision = 18, scale = 2)
    private Double preco;

    @ManyToOne // com join Column tem que especificar o tipo de relacionamento -> ManytoOne nesse caso
    @JoinColumn(name = "id_autor")
    private Autor autor;
    //criando a chave estrangeira baseando em objeto relacional
}
