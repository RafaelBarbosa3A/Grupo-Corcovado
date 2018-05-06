package br.senac.corcovado.controller;

import br.senac.corcovado.controller.adapter.ItemCarrinho;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ComercioJsonController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private ProdutoVendidoRepository pvRepo;
    
    @GetMapping(value = "/comercio/produto_json")
    @ResponseBody
    public Iterable<Produto> listProd() {
        Iterable<Produto> produtos = prodRepo.findAll();
        produtos.forEach((p) -> { p.setPreco(p.getPrecos().get(0).getPreco()); });
        return produtos;
    }
    

    @GetMapping(value = "/comercio/carrinho_json")
    @ResponseBody
    public Venda listCart() {        
        return vendaRepo.findById(1L).get();
    }
    
    @PostMapping(value = "/comercio/carrinho_json/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addCart(@RequestBody ItemCarrinho item) {
        Venda venda = vendaRepo.findById(1L).get();
        
        Produto produto;
        if(prodRepo.existsById(item.produtoId)) {
            produto = prodRepo.findById(item.produtoId).get();
            // TODO criar uma melhor seleção de preços
            // produto.setPreco( produto.getPrecos().get(0).getPreco() );
        } else {
            return new ModelAndView("redirect:/comercio/carrinho_json");
        }
        
        ProdutoVendido prodVend = venda.getProdutoVendidos()
                .stream()
                .filter((pv) -> { return pv.getProduto().equals(produto); })
                .findFirst().orElse(null);
        
        if (prodVend != null) {
            prodVend.setQuantidade(prodVend.getQuantidade() + item.quantidade);
            prodVend.setPrecoTotal(prodVend.getPrecoTotal() + (item.quantidade * produto.getPreco()));
        } else {
            prodVend = new ProdutoVendido();
            prodVend.setProduto(produto);
            prodVend.setVenda(venda);
            prodVend.setQuantidade(item.quantidade);
            prodVend.setPrecoTotal(item.quantidade * produto.getPreco());
        }
        
        pvRepo.save(prodVend);
        
        return new ModelAndView("redirect:/comercio/carrinho_json");
    }
    
    @PostMapping(value = "/comercio/carrinho_json/remove", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView removeCart(@RequestBody ItemCarrinho item) {
        Venda venda = vendaRepo.findById(1L).get();
        
        Produto produto;
        if(prodRepo.existsById(item.produtoId)) {
            produto = prodRepo.findById(item.produtoId).get();
            // TODO criar uma melhor seleção de preços
            // produto.setPreco( produto.getPrecos().get(0).getPreco() );
        } else {
            return new ModelAndView("redirect:/comercio/carrinho_json");
        }
        
        ProdutoVendido prodVend = venda.getProdutoVendidos().stream()
                .filter((pv) -> pv.getProduto().equals(produto))
                .findFirst().orElse(null);
        
        pvRepo.delete(prodVend);
        venda = null; // just to lose any reference
        
        return new ModelAndView("redirect:/comercio/carrinho_json");
    }
    
    @PostMapping(value = "/comercio/carrinho_json/edit", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView editCart(@RequestBody ItemCarrinho item) {
        Venda venda = vendaRepo.findById(1L).get();
        
        Produto produto;
        if(prodRepo.existsById(item.produtoId)) {
            produto = prodRepo.findById(item.produtoId).get();
            // TODO criar uma melhor seleção de preços
            // produto.setPreco( produto.getPrecos().get(0).getPreco() );
        } else {
            return new ModelAndView("redirect:/comercio/carrinho_json");
        }
        
        ProdutoVendido prodVend = venda.getProdutoVendidos()
                .stream()
                .filter((pv) -> { return pv.getProduto().equals(produto); })
                .findFirst().orElse(null);
        
        if (prodVend != null) {
            prodVend.setQuantidade(item.quantidade);
            prodVend.setPrecoTotal(item.quantidade * produto.getPreco());
        } else {
            prodVend = new ProdutoVendido();
            prodVend.setProduto(produto);
            prodVend.setVenda(venda);
            prodVend.setQuantidade(item.quantidade);
            prodVend.setPrecoTotal(item.quantidade * produto.getPreco());
        }
        
        pvRepo.save(prodVend);
        
        return new ModelAndView("redirect:/comercio/carrinho_json");
    }
}