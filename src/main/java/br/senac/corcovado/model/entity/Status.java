package br.senac.corcovado.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */
public enum Status {
    RASCUNHO(1, "Rascunho"),
    PAGAMENTO(2, "Pagamento"),
    ENTREGA(3, "Entrega"),
    ENCERRADO(4, "Encerrado");

    private final int id;
    private final String name;

    private Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override public String toString() {
        return name;
    }

    public static Status valueOf(int id) {
        switch (id) {
            case (1): return RASCUNHO;
            case (2): return PAGAMENTO;
            case (3): return ENTREGA;
            case (4): return ENCERRADO;
            default: return null;
        }
    }
}
