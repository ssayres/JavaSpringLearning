package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.model.Autor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println); // É equivalente a escrever lista.forEach(s -> System.out.println(s));
       // Indica que o método println da instância System.out será chamado para cada elemento da lista.
       // É mais conciso e melhora a legibilidade do código.
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("269d0abc-bbb6-42af-b60b-87b660b806b9");

        repository.deleteById(id);

        //Optional<Autor> possivelAutor = repository.findById(id); // Optional pode ser que exista ou não

       // if(possivelAutor.isPresent()) {

          //  Autor autorEncontrado = possivelAutor.get();
         //   System.out.println("Dados do Autor:");
          //  System.out.println(autorEncontrado);

          //  autorEncontrado.setDataNascimento(LocalDate.of(1962,1,
          //          31) );

           // repository.delete(autorEncontrado); // não existe um método update no repository

       // }
    }

    @Test
    public void deleteTeste(){
        var id = UUID.fromString("48d48326-60c7-4c85-922e-f5efe90ff06b");
        var autor = repository.findById(id).get();
        repository.delete(autor); // deleta por objeto e não por id
    }
}
