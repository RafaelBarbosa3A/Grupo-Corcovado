/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Desconto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Diego
 */
public interface DescontoRepository extends CrudRepository<Desconto, Long>{
    
    /*
    @Query(value = "SELECT", nativeQuery= true)
    Iterable<Desconto> findValidDescontoByProdutoId(Long produto_id);
    */
}
