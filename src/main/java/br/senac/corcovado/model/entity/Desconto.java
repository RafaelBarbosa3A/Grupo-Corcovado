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
public class Desconto implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "desconto") private String desconto;
    @Column(name = "percentual") private double percentual;
    @Column(name = "cod_cupom") private String codigoCupom;
    @Column(name = "created_at") private GregorianCalendar createdAt;
    @Column(name = "updated_at") private GregorianCalendar updatedAt;
    @Column(name = "active" ) private boolean active;

    public Desconto() {
        this.id = 0L;
    }

    public Desconto(Long id, String desconto, double percentual, String cod_cupom, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.desconto = desconto;
        this.percentual = percentual;
        this.codigoCupom = cod_cupom;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public GregorianCalendar getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public GregorianCalendar getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(GregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.createdAt = new GregorianCalendar();
        this.createdAt.setTimeInMillis(timeInMillis);
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updatedAt = new GregorianCalendar();
        this.updatedAt.setTimeInMillis(timeInMillis);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.desconto);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.percentual) ^ (Double.doubleToLongBits(this.percentual) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.codigoCupom);
        hash = 23 * hash + Objects.hashCode(this.createdAt);
        hash = 23 * hash + Objects.hashCode(this.updatedAt);
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
        if (!Objects.equals(this.codigoCupom, other.codigoCupom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.updatedAt, other.updatedAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Desconto{" + "id=" + id + ", desconto=" + desconto + ", percentual=" + percentual + ", cod_cupom=" + codigoCupom + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
