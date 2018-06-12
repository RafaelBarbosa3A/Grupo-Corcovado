/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller.adapter;

import br.senac.corcovado.model.entity.Papel;
import br.senac.corcovado.model.entity.Pessoa;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Diego
 */
public class Auth {
    private long id;
    private String username;
    private String name;
    private List<String> authorities;
    private boolean authenticated;

    public Auth() {
        this.authenticated = false;
    }

    public Auth(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.username = pessoa.getEmail();
        this.name = pessoa.getNome();
        this.authorities = pessoa.getAuthorities().stream().map(Papel::getAuthority).collect(Collectors.toList());
        this.authenticated = true;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return "Auth{" + "id=" + id + ", username=" + username + ", name=" + name + ", authorities=" + authorities + ", authenticated=" + authenticated + '}';
    }
}
