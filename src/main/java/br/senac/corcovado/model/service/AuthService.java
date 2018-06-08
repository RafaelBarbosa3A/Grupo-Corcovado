/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.service;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Pessoa;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class AuthService {
    
    public Auth getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Pessoa) {
            return new Auth((Pessoa) principal);
        } else {
            return new Auth();
        }
    }
}
