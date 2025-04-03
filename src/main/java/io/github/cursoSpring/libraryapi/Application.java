package io.github.cursoSpring.libraryapi;

import io.github.cursoSpring.libraryapi.model.Autor;
import io.github.cursoSpring.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//exemploSalvarRegistro(repository);
	}
	//public static void exemploSalvarRegistro(AutorRepository autorRepository){
		//Autor autor = new Autor();
		//autor.setNome("José");
	//	autor.setNacionalidade("Brasileira");
	//	autor.setDataNascimento(LocalDate.of(1950,1,31));

	//	var autorSalvo = autorRepository.save(autor);
	//	System.out.println("Autor Salvo " + autorSalvo);

	//}

}
