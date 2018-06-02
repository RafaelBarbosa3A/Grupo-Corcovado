/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Pessoa;
import java.security.Principal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */

@RestController
public class UserRestController {
    
    @RequestMapping("/user")
    public Pessoa user() {
        return (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
