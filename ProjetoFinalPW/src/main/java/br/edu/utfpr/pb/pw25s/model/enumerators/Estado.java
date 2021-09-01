package br.edu.utfpr.pb.pw25s.model.enumerators;

public enum Estado {

    AC(12,"Acre"),
    AL(27,"Alagoas"),
    AM(13,"Amazonas"),
    AP(16,"Amapá"),
    BA(29,"Bahia"),
    CE(23,"Ceará"),
    ES(32,"Espírito Santo"),
    GO(52,"Goiás"),
    MA(21,"Maranhão"),
    MT(51,"Mato Grosso"),
    MS(50,"Mato Grosso do Sul"),
    MG(31,"Minas Gerais"),
    PA(15,"Pará"),
    PB(25,"Paraíba"),
    PR(41,"Paraná"),
    PE(26,"Pernambuco"),
    PI(22,"Piauí"),
    RN(24,"Rio Grande do Norte"),
    RS(43,"Rio Grande do Sul"),
    RJ(33,"Rio de Janeiro"),
    RO(11,"Rondônia"),
    RR(14,"Roraima"),
    SC(42,"Santa Catarina"),
    SP(35,"São Paulo"),
    SE(28,"Sergipe"),
    TO(17,"Tocantins"),
    DF(53,"Distrito Federal");

    private final int codigoIbge;
    private final String nome;

    Estado(int codigoIbge,String nome) {
        this.codigoIbge = codigoIbge;
        this.nome = nome;
    }

    public String getCodigo() {
        return Integer.toString(codigoIbge);
    }

    public String getNome(){
        return nome;
    }

    public int getCodigoIbge(){
        return codigoIbge;
    }

}