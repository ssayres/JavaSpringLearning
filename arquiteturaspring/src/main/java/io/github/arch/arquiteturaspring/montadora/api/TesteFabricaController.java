package io.github.arch.arquiteturaspring.montadora.api;

import io.github.arch.arquiteturaspring.montadora.CarroStatus;
import io.github.arch.arquiteturaspring.montadora.Chave;
import io.github.arch.arquiteturaspring.montadora.HondaHRV;
import io.github.arch.arquiteturaspring.montadora.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    @Autowired
    //@Qualifier("motorTurbo")
    @Aspirado
    private Motor motor;

    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave){
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }

}
