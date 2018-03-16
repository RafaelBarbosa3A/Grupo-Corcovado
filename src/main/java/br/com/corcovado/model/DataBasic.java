package br.com.corcovado.model;

import java.util.GregorianCalendar;

/**
 *
 * @author wesley
 */
public class DataBasic {

    private Long id;
    private boolean ativo;
    private GregorianCalendar criado;    

    public DataBasic() {
        this.id = -1l;
        this.ativo = true;
    }

    public DataBasic(Long id, boolean ativo) {
        this.id = id;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public GregorianCalendar getCriado() {
        return criado;
    }

    public void setCriado(GregorianCalendar criado) {
        this.criado = criado;
    }

    public void setCriado(long timeInMillis) {
        this.criado = new GregorianCalendar();
        this.criado.setTimeInMillis(timeInMillis);
    }
}
