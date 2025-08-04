package io.github.cursoSpring.libraryapi.service;

import io.github.cursoSpring.libraryapi.model.Livro;
import io.github.cursoSpring.libraryapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository repository;

    // Construtor manual
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    // Adicione aqui os métodos que usarem o repository
}
