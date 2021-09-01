package br.edu.utfpr.pb.pw25s.service.impl;

import br.edu.utfpr.pb.pw25s.model.Compra;
import br.edu.utfpr.pb.pw25s.repository.CompraRepository;
import br.edu.utfpr.pb.pw25s.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl extends CrudServiceImpl<Compra, Long> implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    protected JpaRepository<Compra, Long> getRepository() { return compraRepository; }

}
