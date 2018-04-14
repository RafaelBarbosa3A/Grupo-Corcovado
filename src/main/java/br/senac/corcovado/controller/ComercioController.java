/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;

/**
 *
 * @author Diego
 */
@Controller
public class ComercioController {
    @Autowired 
    private ProdutoRepository produtoRepo;
    @Autowired 
    private PrecoRepository precoRepo;
    @Autowired 
    private VendaRepository vendaRepo;
    @Autowired 
    private ProdutoVendidoRepository prodVendRepo;
    
    @GetMapping("/comercio")
    public ModelAndView list() {
        // TODO adicionar paramtros de busca
        return new ModelAndView("/comercio/produto_list").addObject("produtos", produtoRepo.findAll());
    }
    
    @RequestMapping(value = "/comercio/addToCart", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCart(@RequestParam("produtoId") Long produtoId, 
            @RequestParam("quantidade") Integer quantidade) {
        
        //TODO Se não for possivel montar na view montar um builder/factory para ProdutoVendido.class
        
        Venda venda = vendaRepo.findById(1L).get(); /* TODO encotrar uma venda baseada no usuário logado, depende de Spring security QUE O TSUDA AINDA NÃO PASSOU!!! */
        Produto produto = produtoRepo.findById(produtoId).get();
        Iterable<Preco> precos = precoRepo.findAllByProdutoId(produtoId);
        Preco preco = precos.iterator().next(); // TODO selecionar o preço baseadno no perfil do usuário
        
        ProdutoVendido item = new ProdutoVendido();
        item.setVendaId(venda.getId());
        item.setProdutoId(produto.getId());
        item.setQuantidade(quantidade);
        item.setPrecoTotal(preco.getPreco() * quantidade);
        
        prodVendRepo.save(item);
        
        return "Success";
    }
}
