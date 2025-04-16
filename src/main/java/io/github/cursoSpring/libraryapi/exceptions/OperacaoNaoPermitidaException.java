package io.github.cursoSpring.libraryapi.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{
    public OperacaoNaoPermitidaException(String message){
        super(message);
    }
}
