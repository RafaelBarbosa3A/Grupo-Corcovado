package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public enum Status {
    RASCUNHO("Rascunho"), // lista de produtos a ser comprados.
    PAGAMENTO("Pagamento"), // compra encerrada, aguardando pagamento (ex: boleto).
    ENTREGA("Entrega"), // pagamento encerrado, disponível para entrega.
    ENCERRADO("Encerrado"); // entrega encerrada, processo encerrado.

    private final String nome;
    
    private Status(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override public String toString() {
        return nome;
    }
}
