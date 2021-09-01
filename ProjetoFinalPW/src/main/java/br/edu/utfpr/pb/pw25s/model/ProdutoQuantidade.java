package br.edu.utfpr.pb.pw25s.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoQuantidade {

    private Produto produto;

    private int quantidade;

}
