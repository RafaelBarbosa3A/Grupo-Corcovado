package br.senac.corcovado.model.entity;

import java.util.GregorianCalendar;

/**
 *
 * @author wesley
 */
public class DataBasicModel {

    private Long id;    
    private GregorianCalendar created_at;
    private GregorianCalendar updated_at;
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
        return created_at;
    }

    public void setCreated_at(GregorianCalendar created_at) {
        this.created_at = created_at;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.created_at = new GregorianCalendar();
        this.created_at.setTimeInMillis(timeInMillis);
    }

    public GregorianCalendar getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(GregorianCalendar updated_at) {
        this.updated_at = updated_at;
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updated_at = new GregorianCalendar();
        this.updated_at.setTimeInMillis(timeInMillis);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
