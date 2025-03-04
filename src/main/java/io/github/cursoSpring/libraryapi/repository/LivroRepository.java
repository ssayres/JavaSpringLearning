package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 *
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> { // alt + Enter para criar classe de testes
        //Query Method

    // select * from livro where id_autor = id
    // Melhor forma de carregar - Não utilize EAGER
    List<Livro> findByAutor(Autor autor);

    // select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    // select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = ? isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);


    //JPL -> referencia as entidades e as propriedades
    // select l.* from livro as l order by l.titulo
    @Query("select l from Livro as l order by l.titulo ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            select l.genero from Livro l join l.autor a where nacionalidade = 'Brasileira' order by l.genero
            """) // """ quebra a linha sem precisar concatenar
    List<String> listarGenerosAutoresBrasileiros();

    // named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String nomePropriedade);

    // positional parameters
    @Query("select l from Livro l where l.genero = ?1 order by ?2") // ?numero é a sintaxe de parametro
    List<Livro> findByPositionalParameters(@Param("genero") GeneroLivro generoLivro,
                                           @Param("paramOrdenacao") String nomePropriedade);


    @Modifying
    @Transactional // necessario pra inser update ou delete
    @Query("delete  from Livro  where genero = ?1")
    void deletarPorGenero(GeneroLivro genero);

    @Modifying
    @Transactional // necessario pra inser update ou delete
    @Query("update  Livro  set dataPublicacao = ?1")
    void atualizarDataPublicacao(LocalDate novaData);


}
