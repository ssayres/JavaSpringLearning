package io.github.cursoSpring.libraryapi.controller;

import io.github.cursoSpring.libraryapi.controller.dto.AutorDTO;
import io.github.cursoSpring.libraryapi.controller.dto.ErroCampo;
import io.github.cursoSpring.libraryapi.controller.dto.ErroResposta;
import io.github.cursoSpring.libraryapi.controller.mappers.AutorMapper;
import io.github.cursoSpring.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.cursoSpring.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
// host = http://localhost:8080/autores

@RequiredArgsConstructor
public class AutorController implements GenericController {

    private final AutorService service;
    private final AutorMapper mapper;

//    public AutorController(AutorService service, AutorMapper mapper){
//         argsConstructor é isso aqui, construtor feito automático
//        this.service = service;
//        this.mapper = mapper;
//    }


    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto) {

        try {
            Autor autor = mapper.toEntity(dto);
            service.salvar(autor);

            //http://localhost:8080/autores/USHDUdhKAKkajks
            URI location = gerarHeaderLocation(autor.getId());
            //ErroResposta erro = ErroResposta.conflito("Autor já cadastrado!");
            //return ResponseEntity.status(erro.status().body(erro));
        } catch (RegistroDuplicadoException e){
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        return service.obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = mapper.toDto(autor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(()->ResponseEntity.notFound().build());
//        if(autorOptional.isPresent()){
//            Autor autor = autorOptional.get();
//            AutorDTO dto = mapper.toDto(autor);
//            return ResponseEntity.ok(dto);
//        }
//
//        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deletar(autorOptional.get());
            return ResponseEntity.noContent().build();

        }catch (OperacaoNaoPermitidaException e){
            var erroReposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroReposta.status()).body(erroReposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado = service.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNacionalidade((dto.nacionalidade()));
        autor.setDataNascimento((dto.dataNascimento()));

        service.atualizar(autor);

        return ResponseEntity.noContent().build();
    }



}