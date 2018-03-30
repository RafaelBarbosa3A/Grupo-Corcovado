package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public enum Nivel{

    BASIC(1, "Basic"),
    GOLD(2, "Gold"),
    PLATINUM(3, "Platinum");    
    
    private final int id;
    private final String name;
    
    private Nivel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    @Override public String toString() {
        return name;
    }

    public static Nivel valueOf(int id) {
        switch (id) {
            case (1): return BASIC;
            case (2): return GOLD;
            case (3): return PLATINUM;
            default: return null;
        }
    }
}
