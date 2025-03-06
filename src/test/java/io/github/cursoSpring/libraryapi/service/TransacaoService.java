package io.github.cursoSpring.libraryapi.service;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import io.github.cursoSpring.libraryapi.repository.AutorRepository;
import io.github.cursoSpring.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    @Transactional // tem que ter pra fazer insert delete e update pois o SQL só realiza de fato com transactional(BEGIN,COMMIT)
    public void executar(){
        //salvar autor
        Autor autor = new Autor();
        autor.setNome("Cláudio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1963, 2,10));

        autorRepository.save(autor);

        //salvar livro
        Livro livro = new Livro();
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setIsbn("665233-333573");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Valores Brutais");
        livro.setDataPublicacao(LocalDate.of(1989,2,10));

        if(autor.getNome().equals( "Cláudio")){
            throw new RuntimeException("Rollback!");

            // A operação foi vai acontecer(COMMIT) caso de tudo certo, ou seja o flush só acontece depois disso, .saveAndFlush ignora fluxo e passa por cima do commit;
        }
    }
}
