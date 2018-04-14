package br.senac.corcovado.model.entity;

import java.util.GregorianCalendar;

/**
 *
 * @author wesley
 */
public class DataBasicModel {

    private Long id;    
    private GregorianCalendar createdAt;
    private GregorianCalendar updatedAt;
    private boolean active;

    public DataBasicModel() {
        this.id = -1l;
        this.active = true;
    }

    public DataBasicModel(Long id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GregorianCalendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }    
    
    public void setCreatedAt(long timeInMillis) {
        this.createdAt = new GregorianCalendar();
        this.createdAt.setTimeInMillis(timeInMillis);
    }
    
    public GregorianCalendar getUpdatedAt() {
        return updatedAt;
    }    

    public void setUpdatedAt(GregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setUpdatedAt(long timeInMillis) {
        this.updatedAt = new GregorianCalendar();
        this.updatedAt.setTimeInMillis(timeInMillis);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
