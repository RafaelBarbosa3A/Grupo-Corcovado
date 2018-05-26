/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
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
public class ComercioController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private PrecoRepository precoRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private PessoaRepository pessRepo;
    
    @GetMapping("/comercio")
    public ModelAndView main() {
        // TODO adicionar paramtros de busca
        return new ModelAndView("/comercio/comercio");
    }
    
    @GetMapping("/comercio/list")
    public ModelAndView list() {
        return new ModelAndView("/comercio/_list");
    }
    
    @GetMapping("/comercio/show")
    public ModelAndView show() {
        return new ModelAndView("/comercio/_show");
    }
    
    @GetMapping("/comercio/cart")
    public ModelAndView cart() {
        return new ModelAndView("/comercio/_cart");
    }
    
    @GetMapping("/comercio/finaliza")
    public ModelAndView finaliza() {
        return new ModelAndView("/comercio/_finaliza");
    }
    
    /*
    @GetMapping("/comercio/recibo")
    public ModelAndView recibo() {
        return new ModelAndView("/comercio/_recibo");
    }
    */
    
    @GetMapping("/comercio/recibo/{id}")
    public ModelAndView recibo(@PathVariable("id") long id) {
        // Apenas permitr ver os pedidos do usuário logado.
        return new ModelAndView("/comercio/recibo").addObject("venda", vendaRepo.findById(id).get());
    }
}
