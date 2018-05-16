/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @PostMapping("/comercio/entrega")
    public ModelAndView checkout (@RequestParam("cartId") long cartId,
    		@RequestParam("enderecoId") long enderecoId, 
    		@RequestParam("frete") double frete,
    		@RequestParam("pagamento") String pagamento,
    		@RequestParam("cartao") String cartao) {
    	Venda venda = vendaRepo.findById(cartId).get();
    	
    	//TODO centralize methods for calculate the fields in venda entity
    	//TODO add frete attribute
    	//TODO insert pagamento into venda
    	
    	vendaRepo.save(venda);    	
        return new ModelAndView("/comercio/entrega");
    }
}
