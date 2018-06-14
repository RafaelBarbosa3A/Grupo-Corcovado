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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "sac")
public class Sac implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    
    @Column(name = "pessoa_id") private Pessoa pessoa;
    
    @Column(name = "contato") private String contato;
    
    @Size(max = 1024)
    @NotEmpty(message = "Favor digitar uma mensagem")
    @Column(name = "mensagem", length=1024) private String mensagem;
    
    @Column(name = "status_mensagem") private StatusMensagem statusMensagem;
    
    @Column(name = "created_at") private long createdAt;
    @Column(name = "updated_at") private long updatedAt;
    @Column(name = "active") private boolean active;

    public Sac() {
        this.id = 0L;
    }

    public Long getId() {
        return id;
    }
}
