package br.edu.utfpr.pb.pw25s.model.enumerators;

public enum TipoArtista {

    DIRETOR("Diretor"),
    ATOR("Ator");

    private String descricao;

    TipoArtista(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
