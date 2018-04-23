/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.Utils;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Iterator;

/**
 *
 * @author Diego
 */
@Controller
public class ComercioController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private PrecoRepository precoRepo;
    /*
    @Autowired private VendaRepository vendaRepo;
    @Autowired private ProdutoVendidoRepository prodVendRepo;
    */
    @GetMapping("/comercio")
    public ModelAndView main() {
        // TODO adicionar paramtros de busca
        Iterable<Produto> produtos = prodRepo.findAll();
        for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext();) {
            Produto next = iterator.next();
            next.setPrecos(Utils.asList(precoRepo.findAllByProdutoId(next.getId())));
        }
        return new ModelAndView("/comercio/comercio").addObject("produtos", produtos);
    }

    /*
    @RequestMapping(value = "/comercio/addToCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<ProdutoVendido> addCart(@RequestParam("produtoId") Long produtoId, @RequestParam("quantidade") Integer quantidade) {

        //TODO Se não for possivel montar na view montar um builder/factory para ProdutoVendido.class
        Venda venda = vendaRepo.findById(1L).get();

        Produto produto = produtoRepo.findById(produtoId).get();
        Iterable<Preco> precos = precoRepo.findAllByProdutoId(produtoId);
        Preco preco = precos.iterator().next(); // TODO selecionar o preço baseadno no perfil do usuário

        if(quantidade == null) {
            quantidade = 1;
        }
        /* Cria ou modifica ProdutoVendido * /
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
    */
}
