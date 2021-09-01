package br.edu.utfpr.pb.pw25s.service.impl;

import br.edu.utfpr.pb.pw25s.model.Cliente;
import br.edu.utfpr.pb.pw25s.repository.ClienteRepository;
import br.edu.utfpr.pb.pw25s.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, Long>  implements ClienteService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected JpaRepository<Cliente, Long> getRepository() { return clienteRepository; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.clienteRepository.findByEmail(email).orElseThrow( ()
                -> new UsernameNotFoundException("Cliente não encontrado")
        );
    }

    public void resetSenha(Cliente cliente, String novaSenha) {
        String hashSenha = passwordEncoder.encode(novaSenha);
        cliente.setSenha(hashSenha);
        cliente.setConfirmacaoSenha(hashSenha);
        clienteRepository.save(cliente);
    }

}
