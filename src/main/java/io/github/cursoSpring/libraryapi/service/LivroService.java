package io.github.cursoSpring.libraryapi.service;

import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import io.github.cursoSpring.libraryapi.repository.LivroRepository;
import io.github.cursoSpring.libraryapi.repository.specs.LivroSpecs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.cursoSpring.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    // Construtor manual
//    public LivroService(LivroRepository repository) {
//        this.repository = repository;
//    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    // Adicione aqui os métodos que usarem o repository

    public void deletar(Livro livro){

        repository.delete(livro);
    }

    //isbn, titulo, nome autor, genero, ano de publicacao
    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer dataPublicacao){


        // select + from livro where isbn = :isbn and nomeAutor =

//        Specification<Livro> specs = Specification.where(LivroSpecs.isbnEquals(isbn)).and(LivroSpecs.tituloLike(titulo)
//                .and(LivroSpecs.generoEquals(genero)));

        // select * from Livro where 0 = 0 / mesma coisa que onde é verdadeiro
        Specification<Livro> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if( isbn != null ){
            // query = :query and isbn = :isbn
            specs = specs.and(isbnEquals(isbn));
        }
        if( titulo != null ){
            specs = specs.and(tituloLike(titulo));
        }

        if( titulo != null){

            specs = specs.and(tituloLike(titulo));

        } if( genero != null ){
            specs = specs.and(generoEquals(genero));
        }

        return repository.findAll(isbnEquals(isbn));

    }



}
