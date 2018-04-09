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

    public GregorianCalendar getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }    
    
    public void setCreated_at(long timeInMillis) {
        this.createdAt = new GregorianCalendar();
        this.createdAt.setTimeInMillis(timeInMillis);
    }
    
    public GregorianCalendar getUpdated_at() {
        return updatedAt;
    }    

    public void setUpdated_at(GregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setUpdated_at(long timeInMillis) {
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
