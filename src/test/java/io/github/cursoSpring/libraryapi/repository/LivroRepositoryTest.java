package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
}