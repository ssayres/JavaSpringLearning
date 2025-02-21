package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> { // alt + Enter para criar classe de testes
        //Query Method

    // select * from livro where id_autor = id
    // Melhor forma de carregar - Não utilize EAGER
    List<Livro> findByAutor(Autor autor);




}
