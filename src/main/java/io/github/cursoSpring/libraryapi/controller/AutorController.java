package io.github.cursoSpring.libraryapi.controller;

import io.github.cursoSpring.libraryapi.controller.dto.AutorDTO;
import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("autores")
// host = http://localhost:8080/autores
public class AutorController {

    private final AutorService service;
    public AutorController(AutorService service){

        this.service = service;
    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody  AutorDTO autor){

        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        //http://localhost:8080/autores/USHDUdhKAKkajks
        URI location =ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
