package br.edu.utfpr.pb.pw25s.repository;

import br.edu.utfpr.pb.pw25s.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long>  {

    Optional<List<Compra>> findComprasByClienteIdOrderByIdDesc(Long clienteId);

}
