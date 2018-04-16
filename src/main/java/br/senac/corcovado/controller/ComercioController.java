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
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public Iterable<ProdutoVendido> addCart(@RequestParam("produtoId") Long produtoId, 
            @RequestParam("quantidade") Integer quantidade) {
        
        //TODO Se não for possivel montar na view montar um builder/factory para ProdutoVendido.class
        Venda venda = vendaRepo.findById(1L).get(); /* TODO encotrar uma venda baseada no usuário logado, depende de Spring security QUE O TSUDA AINDA NÃO PASSOU!!! */
        
        Produto produto = produtoRepo.findById(produtoId).get();
        Iterable<Preco> precos = precoRepo.findAllByProdutoId(produtoId);
        Preco preco = precos.iterator().next(); // TODO selecionar o preço baseadno no perfil do usuário
        
        if(quantidade == null) {
            quantidade = 1;
        }
        /* Cria ou modifica ProdutoVendido */
        ProdutoVendido prodVenda;
        if (prodVendRepo.existsByVendaIdAndProdutoId(venda.getId(), produtoId)) {
            prodVenda = prodVendRepo.findByVendaIdAndProdutoId(venda.getId(), produtoId).get();
            prodVenda.setQuantidade(prodVenda.getQuantidade() + quantidade);
            
        } else {
            prodVenda = new ProdutoVendido();
            prodVenda.setVendaId(venda.getId());
            prodVenda.setProdutoId(produto.getId());
            prodVenda.setQuantidade(quantidade);
        }
        
        prodVenda.setPrecoTotal(prodVenda.getQuantidade() * preco.getPreco());
        prodVendRepo.save(prodVenda);
        
        return prodVendRepo.findByVendaId(venda.getId());
    }
}
