package io.github.cursoSpring.libraryapi.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value(("${spring.datasource.url}"))
    String url;
    @Value(("${spring.datasource.username}"))
    String username;
    @Value(("${spring.datasource.password}"))
    String password;
    @Value(("${spring.datasource.driver-class-name}"))
    String driver;

    //@Bean
    public DataSource dataSource(){ // não recomendado para produção

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // vai liberar 10 conexoes
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(100000); //timeout para conseguir nova conexao
        config.setConnectionTestQuery("select 1"); // testar se está conectando com o banco
        return new HikariDataSource(config);
    }
}
