package br.edu.utfpr.pb.pw25s.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class SenhaResetToken {

    public SenhaResetToken(String token, Cliente cliente) {
        this.token = token;
        this.cliente = cliente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cliente_id")
    private Cliente cliente;

    private Date dataValidade;

}
