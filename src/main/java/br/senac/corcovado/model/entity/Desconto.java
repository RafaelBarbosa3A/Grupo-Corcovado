package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Desconto extends DataBasic{
    private String desconto;
    private double percentual;
    private String cod_cupom;

    public Desconto() {
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
