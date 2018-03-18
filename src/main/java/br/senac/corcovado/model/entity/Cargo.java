package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Cargo extends DataBasicEnum{

    public Cargo() {
    }

    public Cargo(Long id, String nome, String descricao) {
        super(id, nome, descricao);
    }
}
