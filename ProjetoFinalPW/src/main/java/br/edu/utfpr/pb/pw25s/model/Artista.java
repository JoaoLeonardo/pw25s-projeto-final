package br.edu.utfpr.pb.pw25s.model;

import br.edu.utfpr.pb.pw25s.model.enumerators.TipoArtista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Artista {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String apelido;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    @ManyToMany(mappedBy = "diretores")
    public List<Produto> filmesDirigidos;

}
