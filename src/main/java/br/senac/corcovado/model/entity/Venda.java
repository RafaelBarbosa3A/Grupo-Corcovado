package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;


    @ManyToOne(targetEntity = Pessoa.class, optional = true)
    @JoinColumn(name = "pessoa_id",
            referencedColumnName = "id",
            nullable = true)
    private Pessoa pessoa;

    @Column(name = "endereco_id") private Long enderecoId;
    @Column(name = "desconto_id") private Long descontoId; //to remove
    @Column(name = "status_id") private int statusId;
    @Column(name = "frete") private Double frete;
    @Column(name = "total") private Double total;
    @Column(name = "pagamento") private String pagamento;
    @Column(name = "comprovante") private String comprovante;
    @Column(name = "prazo_entrega") private String prazoEntrega;
    @Column(name = "codigo_rastreamento") private String codigoRastreamento;

    @OneToMany(targetEntity = ProdutoVendido.class,
            mappedBy = "venda",
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<ProdutoVendido> produtoVendidos;

    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;

    public Venda() {
        this.id = 0L;
        this.statusId = 1;
        this.total = 0D;
        this.frete = 0D;
        this.produtoVendidos = new HashSet();
    }

    public Venda(Pessoa pessoa) {
        this.id = 0L;
        this.pessoa = pessoa;
        this.statusId = 1;
        this.total = 0D;
        this.frete = 0D;
        this.produtoVendidos = new HashSet();
    }

    public Venda(Long id, Pessoa pessoa, Long enderecoId, Long descontoId, int statusId, Double frete, Double total, String pagamento, String comprovante, String prazoEntrega, String codigoRastreamento, Set<ProdutoVendido> produtoVendidos, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.pessoa = pessoa;
        this.enderecoId = enderecoId;
        this.descontoId = descontoId;
        this.statusId = statusId;
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

    public Long getEnderecoId() {
        return enderecoId;
    }
    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Long getDescontoId() {
        return descontoId;
    }
    public void setDescontoId(Long descontoId) {
        this.descontoId = descontoId;
    }

    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    public String getPagamento() {
        return pagamento;
    }
    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getComprovante() {
        return comprovante;
    }
    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public String getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(String prazoEntrega) {
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
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.pessoa);
        hash = 37 * hash + Objects.hashCode(this.enderecoId);
        hash = 37 * hash + Objects.hashCode(this.descontoId);
        hash = 37 * hash + this.statusId;
        hash = 37 * hash + Objects.hashCode(this.total);
        hash = 37 * hash + Objects.hashCode(this.pagamento);
        hash = 37 * hash + Objects.hashCode(this.comprovante);
        hash = 37 * hash + Objects.hashCode(this.prazoEntrega);
        hash = 37 * hash + Objects.hashCode(this.codigoRastreamento);
        hash = 37 * hash + Objects.hashCode(this.createdAt);
        hash = 37 * hash + Objects.hashCode(this.updatedAt);
        hash = 37 * hash + (this.active ? 1 : 0);
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
        return "Venda{" + "id=" + id + ", pessoa=" + pessoa + ", enderecoId=" + enderecoId + ", descontoId=" + descontoId + ", statusId=" + statusId + ", total=" + total + ", pagamento=" + pagamento + ", comprovante=" + comprovante + ", prazoEntrega=" + prazoEntrega + ", codigoRastreamento=" + codigoRastreamento + ", produtoVendidos=" + produtoVendidos + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
