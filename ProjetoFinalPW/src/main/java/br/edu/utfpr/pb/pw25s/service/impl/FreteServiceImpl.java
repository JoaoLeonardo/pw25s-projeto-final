package br.edu.utfpr.pb.pw25s.service.impl;

import br.edu.utfpr.pb.pw25s.model.Frete;
import br.edu.utfpr.pb.pw25s.repository.FreteRepository;
import br.edu.utfpr.pb.pw25s.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FreteServiceImpl extends CrudServiceImpl<Frete, Long> implements FreteService {

    @Autowired
    private FreteRepository freteRepository;

    @Override
    protected JpaRepository<Frete, Long> getRepository() { return freteRepository; }

}

