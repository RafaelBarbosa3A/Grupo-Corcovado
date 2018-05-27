package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "produto")
@SQLDelete(sql = "UPDATE produto SET active = false WHERE id = ?")
@Loader(namedQuery = "findProdutoById")
@NamedQuery(name = "findProdutoById", query = "SELECT p FROM Produto p WHERE p.id = ?1")
@Where(clause = "active = true")
//Gambiarraaaaaaaaa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "precos"})
public class Produto implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") private Long id;
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1,max=255,message="Favor digitar um nome entre 1 á 255 letras")
    @Column(name = "nome") private String nome;
    
    @NotEmpty(message = "Favor digitar uma descrição")
    @Size(min=1,max=255,message="Favor digitar uma descrição entre 1 á 255 letras")
    @Column(name = "descricao") private String descricao;
    
    @NotEmpty(message = "Favor digitar um nome de fabricante")
    @Size(min=1,max=255,message="Favor digitar um nome de fabricante entre 1 á 255 letras")
    @Column(name = "fabricante") private String fabricante;
    
    @NotEmpty(message = "Favor digitar um código")
    @Size(min=1,max=255,message="Favor digitar um código entre 1 á 255 letras")
    @Column(name = "codigo") private String codigo;
    
    @Column(name = "imagem") private String imagem;
    
    @Min(value = 0, message = "Estoque não pode ser negativo")
    @Column(name = "estoque") private int estoque;
    
    @Min(value = 0, message = "Reservado não pode ser negativo")
    @Column(name = "reservado") private int reservado;

    @OneToMany(mappedBy = "produto") private List<Desconto> Desconto;
    
    
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", referencedColumnName = "id") 
    private Categoria categoria;
    
    // @OneToMany(mappedBy = "produto") private List<Preco> precos;
    
    // Mantem o preco selecionado (baseado no perfil do usuario).
    // NOPE! NOOO!!! STUPID FUCKING IDEA!!!!!
    // replaced
    // @Transient private Double preco;
    
    @NotEmpty(message = "Favor insira um preço")
    @Min(0)
    @Column(name = "preco")
    private Double preco;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;
    
    public Produto() {
        this.id = 0L;
        this.active = true;
    }
    
    // === JPA Porco ===

    @PrePersist 
    private void beforeCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
    
    @PreRemove
    private void softDelete() {
        this.active = false;
    }

    /*
    @PostLoad
    private void loadPreco() {
        this.preco = this.precos.stream()
                .sorted().findFirst()
                .orElse(new Preco(0L, 0D, this, Nivel.BASIC, System.currentTimeMillis(), System.currentTimeMillis()))
                .getPreco();
    }
    */
}
