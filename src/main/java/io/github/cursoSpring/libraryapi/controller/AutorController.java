package io.github.cursoSpring.libraryapi.controller;

import io.github.cursoSpring.libraryapi.controller.dto.AutorDTO;
import io.github.cursoSpring.libraryapi.model.Autor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autores")
// host = http://localhost:8080/autores
public class AutorController {


    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity salvar(@RequestBody  AutorDTO autor){

        return new ResponseEntity("Autor salvo com sucesso!" + autor, HttpStatus.CREATED);
    }
}
