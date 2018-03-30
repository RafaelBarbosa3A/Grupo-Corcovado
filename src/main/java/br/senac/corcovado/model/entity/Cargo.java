package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public enum Cargo{
    
    DIRETOR(1, "Diretor"),
    GERENTE(2, "Gerente"),
    OPERACIONAL(3, "Operacional");
    
    private final int id;
    private final String name;
    
    private Cargo(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    @Override public String toString() {
        return name;
    }

    public static Cargo valueOf(int id) {
        switch (id) {
            case (1): return DIRETOR;
            case (2): return GERENTE;
            case (3): return OPERACIONAL;
            default: return null;
        }
    }
}
