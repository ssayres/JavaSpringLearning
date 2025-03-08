package io.github.cursoSpring.libraryapi.repository;

import io.github.cursoSpring.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    AutorRepository autorRepository;
    @Autowired
    TransacaoService transacaoService;

    /**
     * Begin -> abre a query
     * Commit -> manda as alterações pro banco
     * Rollback -> desfaz as alterações do banco
     */

    @Test

    public void transacaoTeste(){

        transacaoService.executar();

    }

    @Test

    public void transacaoTesteSemAtualizar(){

        transacaoService.atualizarSemAtualizar();

    }


}
