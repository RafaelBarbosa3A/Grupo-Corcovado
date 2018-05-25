/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class PessoaService implements UserDetailsService {

    @Autowired PessoaRepository pessRepo;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        return pessRepo.findByEmail(string).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
