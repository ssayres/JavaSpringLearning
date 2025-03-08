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
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    // tem que ter pra fazer insert delete e update pois o SQL só realiza de fato com transactional(BEGIN,COMMIT)
    public void executar() {
        //salvar autor
        Autor autor = new Autor();
        autor.setNome("Cláudio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1963, 2, 10));

        autorRepository.save(autor);

        //salvar livro
        Livro livro = new Livro();
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setIsbn("665233-333573");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Valores Brutais");
        livro.setDataPublicacao(LocalDate.of(1989, 2, 10));

        if (autor.getNome().equals("Cláudio")) {
            throw new RuntimeException("Rollback!");

            // A operação foi vai acontecer(COMMIT) caso de tudo certo, ou seja o flush só acontece depois disso, .saveAndFlush ignora fluxo e passa por cima do commit;
        }
    }

    @Transactional
    public void atualizarSemAtualizar() {
        var livro = livroRepository
                .findById(UUID.fromString("4edfde9b-c0d5-4c4b-9758-ea949b38eaec"))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 12, 12));

        //com o transactional, com a mudança do estado de entidade ja muda no banco sem usar o save
        //Quando isso é útil? salvar sem o save pra confirmar?
        // Exemplo de salvar livro com foto -> salvar foto do livro na nuvem -> bucket


    }
}
