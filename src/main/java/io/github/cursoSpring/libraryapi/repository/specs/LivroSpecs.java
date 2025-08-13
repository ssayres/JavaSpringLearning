package io.github.cursoSpring.libraryapi.repository.specs;

import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import org.springframework.data.jpa.domain.Specification;

import java.awt.geom.GeneralPath;

public class LivroSpecs {

    public static Specification<Livro> isbnEquals(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo) {
        //upper(livro.titulo) like( %:param%)
        return (root, query, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }
    public static Specification<Livro> generoEquals(GeneroLivro genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }
}
