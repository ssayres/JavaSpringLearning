package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest // Anotação para rodar algo como teste em ApplicationTests
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;
    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("José Bezerra");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1952,1,31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo:" + autorSalvo);

    }

    @Test
    public void atualizarTest(){
       var id = UUID.fromString("ca126cb3-2f71-40c6-87fe-45cf949b3d7f");

        Optional<Autor> possivelAutor = repository.findById(id); // Optional pode ser que exista ou não

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1962,1,
            31) );

            repository.save(autorEncontrado); // não existe um método update no repository

        }
    }
}
