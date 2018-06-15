/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diego
 */
@Controller
public class EstaticoController {
    @GetMapping("/estatico/institucional")
    public ModelAndView corp() {
        return new ModelAndView("estatico/institucional");
    }
    
    @GetMapping("/estatico/sobre")
    public ModelAndView about() {
        return new ModelAndView("estatico/sobre");
    }
    
    @GetMapping("/estatico/faq")
    public ModelAndView faq() {
        return new ModelAndView("estatico/faq");
    }
        
    @GetMapping("/estatico/troca")
    public ModelAndView refund() {
        return new ModelAndView("estatico/troca");
    }
        
    @GetMapping("/estatico/privacidade")
    public ModelAndView privacy() {
        return new ModelAndView("estatico/privacidade");
    }
}
