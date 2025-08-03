package io.github.cursoSpring.libraryapi.controller.mappers;

import io.github.cursoSpring.libraryapi.controller.dto.AutorDTO;
import io.github.cursoSpring.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome", target="nome")
    @Mapping(source = "dataNascimento", target="dataNascimento")
    @Mapping(source = "nacionalidade", target="nacionalidade")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDto(Autor autor);


}
