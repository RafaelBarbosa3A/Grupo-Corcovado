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
    
    private final String name;
    
    private Nivel(String name) {
        this.name = name;
    }
    
    @Override public String toString() {
        return name;
    }
}
