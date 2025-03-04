package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


/**
 * @see LivroRepositoryTest
 */
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;


    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("98887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO EXPLOSIVO");
        livro.setDataPublicacao(LocalDate.of(1980, 1 ,28));



        Autor autor = autorRepository.findById(UUID.fromString("ca126cb3-2f71-40c6-87fe-45cf949b3d7f")).orElse(null);
        livro.setAutor(autor);

        repository.save(livro);



    }

    @Test
    void salvarCascadeTest(){ // só funciona com  @ManyToOne(cascade = CascadeType.ALL) na entidade livro
        Livro livro = new Livro();
        livro.setIsbn("98887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO EXPLOSIVO");
        livro.setDataPublicacao(LocalDate.of(1980, 1 ,28));

        Autor autor = new Autor();
        autor.setNome("Bruno");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));


        livro.setAutor(autor);

        repository.save(livro);



    }

    @Test
    void salvarSemCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("98887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO EXPLOSIVO");
        livro.setDataPublicacao(LocalDate.of(1980, 1 ,28));

        Autor autor = new Autor();
        autor.setNome("Bruno");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("3a231377-a9f3-48b4-9633-6a10ff1677ad");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("2023e711-9df1-46aa-a7b5-2df6a4d8661a");
        Autor joseBezerra = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(joseBezerra);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("3a231377-a9f3-48b4-9633-6a10ff1677ad");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){ // habilitando o cascade la na entidade livro ele apaga o autor junto
        UUID id = UUID.fromString("3a231377-a9f3-48b4-9633-6a10ff1677ad");
        repository.deleteById(id);
    }

    @Test
    @Transactional // essa transação abre execuções no banco
    void buscarLivroTest(){
        UUID id = UUID.fromString("cfcf1e42-6248-493e-aa8c-d26bf28011c7");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro :");
        System.out.println(livro.getTitulo());
        System.out.println("Autor :");
        System.out.println(livro.getAutor().getNome());
    }


    @Test
    void pesquisarPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("UFO EXPLOSIVO");
        lista.forEach(System.out::println);
    }
    @Test
    void pesquisarPorIsbnTest(){
        List<Livro> lista = repository.findByIsbn("98887-84875");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloePreco(){
        var preco = BigDecimal.valueOf(74);
        var tituloPesquisa = "Casa assombrada 2";
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa,preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarNomesDiferentesLivros(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    // named parameters -> parametros nomeados
    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPOrGeneroPositionalParamTest(){
        var resultado = repository.findByPositionalParameters(GeneroLivro.FICCAO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGenero(){
        repository.deletarPorGenero(GeneroLivro.FICCAO);

    }

    @Test
    void atualizarDataPublicacao(){
        repository.atualizarDataPublicacao(LocalDate.of(2000, 1,1));

    }
}