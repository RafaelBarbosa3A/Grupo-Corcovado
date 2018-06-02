/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Papel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Diego
 */
public interface PapelRepository extends CrudRepository<Papel, Long> {
    Optional<Papel> findByCargo(String cargo);
}
