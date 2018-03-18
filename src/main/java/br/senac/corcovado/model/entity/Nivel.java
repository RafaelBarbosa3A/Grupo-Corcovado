package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Nivel extends DataBasicEnum{

    public Nivel() {
    }

    public Nivel(Long id, String nome, String descricao) {
        super(id, nome, descricao);
    }
}
