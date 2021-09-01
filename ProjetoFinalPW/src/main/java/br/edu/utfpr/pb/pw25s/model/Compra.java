package br.edu.utfpr.pb.pw25s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @NotNull
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "frete_id", referencedColumnName = "id")
    public Frete frete;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.compra")
    private List<CompraProduto> compraProdutos;

    public Double getValorTotal() {
        double total = 0;

        for (CompraProduto item : compraProdutos) {
            total += item.getQuantidade() * item.getValor();
        }
        if (frete.getValorEntrega() != null) {
            total += frete.getValorEntrega();
        }

        return BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

}
