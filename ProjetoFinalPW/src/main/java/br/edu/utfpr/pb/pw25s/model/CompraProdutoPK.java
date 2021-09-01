package br.edu.utfpr.pb.pw25s.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"compra", "produto"})
public class CompraProdutoPK  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private Compra compra;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

}