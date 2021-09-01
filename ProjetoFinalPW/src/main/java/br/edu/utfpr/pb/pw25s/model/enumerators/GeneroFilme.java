package br.edu.utfpr.pb.pw25s.model.enumerators;

public enum GeneroFilme {

    TERROR(1, "terror", "Terror"),
    COMEDIA(2, "comedia", "Comédia"),
    CULT(3, "cult", "Cult");

    private int id;
    private String path;
    private String descricao;

    GeneroFilme(int id, String path, String descricao) {
        this.id = id;
        this.path = path;
        this.descricao = descricao;
    }

    public int getId() { return id; }

    public String getPath() { return path; }

    public String getDescricao() {
        return descricao;
    }

    public static GeneroFilme getByPath(String path) {
        for (int i = 0; i < GeneroFilme.values().length; i++) {
            if (GeneroFilme.values()[i].path.equals(path)) return  GeneroFilme.values()[i];
        }
        return GeneroFilme.values()[0];
    }

}
