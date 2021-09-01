package br.edu.utfpr.pb.pw25s.service.impl;

import br.edu.utfpr.pb.pw25s.model.Homepage;
import br.edu.utfpr.pb.pw25s.repository.HomepageRepository;
import br.edu.utfpr.pb.pw25s.service.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class HomepageServiceImpl extends CrudServiceImpl<Homepage, Long> implements HomepageService {

    @Autowired
    private HomepageRepository homepageRepository;

    @Override
    protected JpaRepository<Homepage, Long> getRepository() { return homepageRepository; }

}
