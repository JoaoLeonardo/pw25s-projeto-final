package br.edu.utfpr.pb.pw25s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProdutoDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int nota;

    @Column(nullable = false)
    private String infoAdicionalLink;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    public Fornecedor fornecedor;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_detalhe_imagem_id", referencedColumnName = "id")
    private List<ProdutoDetalheImagem> imagens;

}