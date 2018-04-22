package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public enum Nivel {
    DESCONTO("Desconto"),
    BASIC("Basic"),
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    BLACK("Black");
    
    private final String nome;
    
    private Nivel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    /*
    @Override public String toString() {
        return nome;
    }
    */
}
