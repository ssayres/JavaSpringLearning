package io.github.cursoSpring.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
@ToString(exclude = "autor")
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name= "preco", precision = 18, scale=2)
    private BigDecimal preco;


    // com join Column tem que especificar o tipo de relacionamento -> ManytoOne nesse caso
    @ManyToOne (
            //(cascade = CascadeType.ALL)
            //fetch = FetchType.LAZY // dessa forma só traz os dados do livro e não do autor/ EAGER traz o fetch do autor junto / Padrão é EAGER
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_autor")
    private Autor autor;
    //criando a chave estrangeira baseando em objeto relacional

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private UUID idusuario;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
