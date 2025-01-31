package io.github.arch.arquiteturaspring;

import io.github.arch.arquiteturaspring.todos.MailSender;
import io.github.arch.arquiteturaspring.todos.TodoEntity;
import io.github.arch.arquiteturaspring.todos.TodoRepository;
import io.github.arch.arquiteturaspring.todos.TodoValidator;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ExemploInjecaoDependencia {
   // public static void main(String[] args) throws SQLException {
     //   DriverManagerDataSource dataSource = new DriverManagerDataSource();
       // dataSource.setUrl("url");
       // dataSource.setUsername("user");
       // dataSource.setPassword("password");

       // Connection connection = dataSource.getConnection();

        //EntityManager entityManager = null;

        //TodoRepository repository = new SimpleJpaRepository<TodoEntity, Integer>();

        //TodoValidator todoValidator = new TodoValidator(repository);
        //MailSender sender = new MailSender();

       // TodoService todoService = new TodoService(repository, todoValidator, sender);

    // BeanGerenciado beanGerenciado = newBeanGerenciado(null);
    // beanGerenciado = setValidator(todoValidator);
        //if(codincao == true){
        //beanGerenciado.setValidator();
    //}
    // }
}
