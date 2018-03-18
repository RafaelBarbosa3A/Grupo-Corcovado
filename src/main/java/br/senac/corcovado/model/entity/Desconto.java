package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Desconto extends DataBasicModel{
    private String desconto;
    private double percentual;
    private String cod_cupom;

    public Desconto() {
    }

    public Desconto(String desconto, double percentual, String cod_cupom) {
        this.desconto = desconto;
        this.percentual = percentual;
        this.cod_cupom = cod_cupom;
    }

    public Desconto(String desconto, double percentual, String cod_cupom, Long id, boolean active) {
        super(id, active);
        this.desconto = desconto;
        this.percentual = percentual;
        this.cod_cupom = cod_cupom;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public String getCod_cupom() {
        return cod_cupom;
    }

    public void setCod_cupom(String cod_cupom) {
        this.cod_cupom = cod_cupom;
    }
}
