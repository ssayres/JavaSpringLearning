package io.github.cursoSpring.libraryapi.repository.specs;

import io.github.cursoSpring.libraryapi.model.GeneroLivro;
import io.github.cursoSpring.libraryapi.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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

    public static Specification<Livro> anoPublicacaoEquals(Integer anoPublicacao) {
        //to char(data_publicacao, 'YYYY') = :anoPublicacao -> isso que vamos fazer
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class, root.get("dataPublicacao"),
                        cb.literal("YYYY")),anoPublicacao.toString());
    }

    public static Specification<Livro> nomeAutorLike(String nome) {
        return (root, query, cb) -> {
            //return cb.like(cb.upper(root.get("autor").get("nome")), "%" + nome.toUpperCase() + "%");
            Join<Object, Object> joinAutor = root.join("autor", JoinType.LEFT);
            return cb.like(cb.upper(joinAutor.get("nome")), nome.toUpperCase() + "%");
        };
    }

}
