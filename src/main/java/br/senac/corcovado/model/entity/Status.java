package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Status extends DataBasicEnum{

    public Status() {
    }

    public Status(Long id, String nome, String descricao) {
        super(id, nome, descricao);
    }
}
