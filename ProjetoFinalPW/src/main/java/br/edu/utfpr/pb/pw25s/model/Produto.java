package br.edu.utfpr.pb.pw25s.model;

import br.edu.utfpr.pb.pw25s.model.enumerators.GeneroFilme;
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
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150, nullable = false)
	private String titulo;
	
	@Column(length = 250, nullable = false)
	private String sinopse;
	
	@Column(nullable = false)
	private Double valor;

	@Column(nullable = false)
	private String imagem;

	@Enumerated(EnumType.STRING)
	private GeneroFilme genero;

	@Column(nullable = false)
	private int ano;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "DiretoresFilmes",
			joinColumns= @JoinColumn(name = "produto_id", referencedColumnName= "id"),
			inverseJoinColumns= @JoinColumn(name = "diretor_id", referencedColumnName= "id")
	)
	public List<Artista> diretores;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_detalhe_id", referencedColumnName = "id")
	private ProdutoDetalhe produtoDetalhe;

}
