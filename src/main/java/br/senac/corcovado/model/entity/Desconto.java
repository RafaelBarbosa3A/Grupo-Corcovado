package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "desconto")
public class Desconto implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "desconto")private String desconto;
    @Column(name = "percentual")private double percentual;
    @Column(name = "cod_cupom")private String cod_cupom;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public Desconto() {
        this.id = 0L;
    }

    public Desconto(Long id, String desconto, double percentual, String cod_cupom, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.desconto = desconto;
        this.percentual = percentual;
        this.cod_cupom = cod_cupom;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GregorianCalendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(GregorianCalendar created_at) {
        this.created_at = created_at;
    }

    public GregorianCalendar getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(GregorianCalendar updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.created_at = new GregorianCalendar();
        this.created_at.setTimeInMillis(timeInMillis);
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updated_at = new GregorianCalendar();
        this.updated_at.setTimeInMillis(timeInMillis);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.desconto);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.percentual) ^ (Double.doubleToLongBits(this.percentual) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.cod_cupom);
        hash = 23 * hash + Objects.hashCode(this.created_at);
        hash = 23 * hash + Objects.hashCode(this.updated_at);
        hash = 23 * hash + (this.active ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Desconto other = (Desconto) obj;
        if (Double.doubleToLongBits(this.percentual) != Double.doubleToLongBits(other.percentual)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.desconto, other.desconto)) {
            return false;
        }
        if (!Objects.equals(this.cod_cupom, other.cod_cupom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.created_at, other.created_at)) {
            return false;
        }
        if (!Objects.equals(this.updated_at, other.updated_at)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Desconto{" + "id=" + id + ", desconto=" + desconto + ", percentual=" + percentual + ", cod_cupom=" + cod_cupom + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
