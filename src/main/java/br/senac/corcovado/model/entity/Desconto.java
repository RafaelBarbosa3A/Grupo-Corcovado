/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "desconto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "produto"})
public class Desconto implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") private Long id;
    
    @Column(name = "preco") private Double preco;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "produto_id", referencedColumnName = "id") private Produto produto;
    
    @Column(name = "inicio", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date inicio;
    
    @Column(name = "fim", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE) 
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date fim;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;

    public Desconto() {
    }

    public Desconto(Long id, Double preco, Produto produto, Date inicio, Date fim, Long createdAt, Long updatedAt) {
        this.id = id;
        this.preco = preco;
        this.produto = produto;
        this.inicio = inicio;
        this.fim = fim;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getInicio() {
        return inicio;
    }
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }
    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.preco);
        hash = 47 * hash + Objects.hashCode(this.inicio);
        hash = 47 * hash + Objects.hashCode(this.fim);
        hash = 47 * hash + Objects.hashCode(this.createdAt);
        hash = 47 * hash + Objects.hashCode(this.updatedAt);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Desconto{" + "id=" + id + ", preco=" + preco + ", inicio=" + inicio + ", fim=" + fim + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
        
    // === JPA Gambiarras ===
    @PrePersist 
    private void beforeCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}
