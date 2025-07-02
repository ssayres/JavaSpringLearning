package io.github.cursoSpring.libraryapi.controller;

import io.github.cursoSpring.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursoSpring.libraryapi.controller.dto.ErroResposta;
import io.github.cursoSpring.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursoSpring.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
public class LivroController {

        private final LivroService service;

        public LivroController(LivroService service) {
                this.service = service;
        }

        @PostMapping
        public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
                try {
                        return ResponseEntity.ok(dto);
                } catch(RegistroDuplicadoException e){
                        var erroDTO = ErroResposta.conflito(e.getMessage());
                        return ResponseEntity.status(erroDTO.status()).body(erroDTO);
                }
        }
}

