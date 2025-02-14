package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> { // alt + Enter para criar classe de testes
}
