package io.github.cursojava.produtosapi.repository;
import io.github.cursojava.produtosapi.model.Produto;
import  org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
//                                                       classe   tipo do id
public interface ProdutoRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNome(String nome);


}
