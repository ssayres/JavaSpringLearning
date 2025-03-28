package io.github.cursoSpring.libraryapi.controller.dto;

import java.time.LocalDate;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {
}

//classe com o construtor embutido