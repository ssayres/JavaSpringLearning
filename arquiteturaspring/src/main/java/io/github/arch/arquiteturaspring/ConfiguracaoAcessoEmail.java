package io.github.arch.arquiteturaspring;

import io.github.arch.arquiteturaspring.todos.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoAcessoEmail {

    @Autowired
    private AppProperties properties;

   // @Bean
  //  public MailSender MailSender(){
     //   return null;
  //  }
}
