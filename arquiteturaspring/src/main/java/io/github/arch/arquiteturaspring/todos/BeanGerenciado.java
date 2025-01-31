package io.github.arch.arquiteturaspring.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


//singleton - estancia unica pra toda a aplicação
//@Lazy - geralmente utilizado pra economizar recursos - nao estancia os Beans logo de cara - na hora de subir a aplicação
@Component
//@Scope("request") // aquele objeto só existe durante a requisição - uma estancia pra cada requisição
//@Scope("singleton")
//@Scope("session")
//@Scope("aplication")  fica salvo na sessão de todos os usuarios
//@Scope(BeanDefinition.SCOPE_PROTOTYPE) uma estancia pra cada usuario - oposto do singleton
//@Scope(WebApplicationContext)
public class BeanGerenciado {

    @Autowired
    private TodoValidator validator;

    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }
    @Autowired
    public BeanGerenciado(TodoValidator validator){
        this.validator = validator;
    }
    @Autowired
    public void setValidator(TodoValidator validator){
        this.validator = validator;
    }
}
