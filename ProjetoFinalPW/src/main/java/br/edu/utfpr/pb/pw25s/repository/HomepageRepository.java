package br.edu.utfpr.pb.pw25s.repository;

import br.edu.utfpr.pb.pw25s.model.Homepage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomepageRepository extends JpaRepository<Homepage, Long> {

    Homepage findTopByOrderByIdDesc();

}
