package br.edu.utfpr.pb.pw25s.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SenhaTrocaDto {

    @NotNull
    private String senha;

    @NotNull
    private String confirmacaoSenha;

    @NotNull
    private String token;

}
