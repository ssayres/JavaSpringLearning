package io.github.arch.arquiteturaspring.montadora;

import java.awt.*;

public class Carro {
    private Montadora montadora;
    private String modelo;
    private Color cor;
    private Motor motor;

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Carro(Motor motor) {
        this.motor = motor;
    }

    public CarroStatus darIgnicao(Chave chave) {
        if(chave.getMontadora() != this.montadora){
            return new CarroStatus("Não é possivel iniciar o carro com esse chave");
        }
        return new CarroStatus("Carro ligado"+motor);
    }
}
