package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public enum Cargo {
    OPERACIONAL("Operacional"),
    GERENTE("Gerente"),
    DIRETOR("Diretor");
    
    private final String nome;
    
    private Cargo(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
}
