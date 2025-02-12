package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//Component e Repository são opcionais - o Repositorio já entende que se trata de um componente repository pois estende do JPA REPOSITORY
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
