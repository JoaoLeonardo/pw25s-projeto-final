package br.edu.utfpr.pb.pw25s.model;

import br.edu.utfpr.pb.pw25s.model.enumerators.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotEmpty(message = "A cidade deve ser preenchida.")
    @Column(nullable = false)
    private String cidade;

    @NotEmpty(message = "O bairro deve ser preenchido.")
    @Column(nullable = false)
    private String bairro;

    @NotEmpty(message = "A rua deve ser preenchida.")
    @Column(nullable = false)
    private String rua;

    @Column
    private String complemento;

    @NotNull(message = "O número deve ser preenchido.")
    @Column(nullable = false)
    private Long numero;

    @NotEmpty(message = "O CEP deve ser preenchido.")
    @Column(nullable = false, length = 9)
    private String cep;


}
