/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;

import br.senac.corcovado.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diego
 */
@Controller
public class NotaController {
    @Autowired 
    private VendaRepository vendaRepo;
    
    @GetMapping("/notas/{id}")
    public ModelAndView nota(@PathVariable long id) {
        return new ModelAndView("/nota/nota").addObject("venda", vendaRepo.findById(id).get());
    }
    
}
