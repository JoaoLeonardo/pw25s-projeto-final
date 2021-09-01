package br.edu.utfpr.pb.pw25s.repository;

import br.edu.utfpr.pb.pw25s.model.SenhaResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenhaResetTokenRepository extends JpaRepository<SenhaResetToken, Long> {

    public SenhaResetToken findByToken(String token);

}
