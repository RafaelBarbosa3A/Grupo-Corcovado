package br.senac.corcovado.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "venda")
@SQLDelete(sql = "UPDATE venda SET active = false WHERE id = ?")
@Loader(namedQuery = "findVendaById")
@NamedQuery(name = "findVendaById", query = "SELECT v FROM Venda v WHERE v.id = ?1")
@Where(clause = "active = true")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createdAtFormat"})
public class Venda implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;

    @ManyToOne(targetEntity = Pessoa.class, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true)
    private Pessoa pessoa;

    //@Column(name = "endereco_id") private Long enderecoId;
    @ManyToOne(targetEntity = Endereco.class, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    private Endereco endereco;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status") private Status status;
        
    @Column(name = "frete") private Double frete;
    @Column(name = "total") private Double total;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento") private Pagamento pagamento;
    
    @Column(name = "comprovante") private String comprovante;
    
    @Column(name = "prazo_entrega")
    @Temporal(javax.persistence.TemporalType.DATE) 
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date prazoEntrega;
    
    @Column(name = "codigo_rastreamento") private String codigoRastreamento;
    
    @OneToMany(targetEntity = ProdutoVendido.class, mappedBy = "venda", fetch = FetchType.EAGER)
    private Set<ProdutoVendido> produtoVendidos;

    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;

    public Venda() {
        this.id = 0L;
        this.status = Status.RASCUNHO;
        this.total = 0D;
        this.frete = 0D;
        this.produtoVendidos = new HashSet();
        this.active = true;
    }

    public Venda(Pessoa pessoa) {
        this.id = 0L;
        this.pessoa = pessoa;
        this.status = Status.RASCUNHO;
        this.total = 0D;
        this.frete = 0D;
        this.produtoVendidos = new HashSet();
        this.active = true;
    }

    public Venda(Long id, Pessoa pessoa, Endereco endereco, Status status, Double frete, Double total, Pagamento pagamento, String comprovante, Date prazoEntrega, String codigoRastreamento, Set<ProdutoVendido> produtoVendidos, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.pessoa = pessoa;
        this.endereco = endereco;
        this.status = status;
        this.frete = frete;
        this.total = total;
        this.pagamento = pagamento;
        this.comprovante = comprovante;
        this.prazoEntrega = prazoEntrega;
        this.codigoRastreamento = codigoRastreamento;
        this.produtoVendidos = produtoVendidos;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getFrete() {
        return frete;
    }
    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public void calculaTotal() {
        this.total = this.produtoVendidos.stream()
                .map(ProdutoVendido::getPrecoTotal)
                .reduce(0D,(acc, pv) -> acc + pv)
                + this.frete;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getComprovante() {
        return comprovante;
    }
    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public Date getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(Date prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getCodigoRastreamento() {
        return codigoRastreamento;
    }
    public void setCodigoRastreamento(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;
    }

    public Set<ProdutoVendido> getProdutoVendidos() {
        return produtoVendidos;
    }
    public void setProdutoVendidos(Set<ProdutoVendido> produtoVendidos) {
        this.produtoVendidos = produtoVendidos;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCreatedAtFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date created = new Date(createdAt);
        return sdf.format(created);
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.frete);
        hash = 23 * hash + Objects.hashCode(this.total);
        hash = 23 * hash + Objects.hashCode(this.pagamento);
        hash = 23 * hash + Objects.hashCode(this.comprovante);
        hash = 23 * hash + Objects.hashCode(this.prazoEntrega);
        hash = 23 * hash + Objects.hashCode(this.codigoRastreamento);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", pessoa=" + pessoa + ", status=" + status + ", frete=" + frete + ", total=" + total + ", pagamento=" + pagamento + ", comprovante=" + comprovante + ", prazoEntrega=" + prazoEntrega + ", codigoRastreamento=" + codigoRastreamento + ", produtoVendidos=" + produtoVendidos + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }

}
