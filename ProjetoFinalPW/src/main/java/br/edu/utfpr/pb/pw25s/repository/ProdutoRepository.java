package br.edu.utfpr.pb.pw25s.repository;

import br.edu.utfpr.pb.pw25s.model.Produto;
import br.edu.utfpr.pb.pw25s.model.enumerators.GeneroFilme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByGenero(GeneroFilme genero);

}












