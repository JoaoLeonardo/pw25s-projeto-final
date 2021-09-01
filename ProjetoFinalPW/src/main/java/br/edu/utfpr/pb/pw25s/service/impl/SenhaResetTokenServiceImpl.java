package br.edu.utfpr.pb.pw25s.service.impl;

import br.edu.utfpr.pb.pw25s.model.Cliente;
import br.edu.utfpr.pb.pw25s.model.SenhaResetToken;
import br.edu.utfpr.pb.pw25s.repository.SenhaResetTokenRepository;
import br.edu.utfpr.pb.pw25s.service.SenhaResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class SenhaResetTokenServiceImpl extends CrudServiceImpl<SenhaResetToken, Long>  implements SenhaResetTokenService {

    @Autowired
    private SenhaResetTokenRepository senhaResetTokenRepository;

    @Override
    protected JpaRepository<SenhaResetToken, Long> getRepository() {
        return senhaResetTokenRepository;
    }

    public void createResetSenhaToken(Cliente cliente, String token) {
        SenhaResetToken novoToken = new SenhaResetToken(token, cliente);

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        novoToken.setDataValidade(calendar.getTime());

        senhaResetTokenRepository.save(novoToken);
    }

    public SenhaResetToken validarSenhaResetToken(String token) {
        final SenhaResetToken resetToken = senhaResetTokenRepository.findByToken(token);

        if (token != null && !isExpirado(resetToken)) {
            return resetToken;
        }

        return null;
    }

    private boolean isExpirado(SenhaResetToken token) {
        final Calendar calendar = Calendar.getInstance();
        return token.getDataValidade().before(calendar.getTime());
    }

}
