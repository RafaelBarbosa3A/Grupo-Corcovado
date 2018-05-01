package br.senac.corcovado.controller;

import java.util.List;
import br.senac.corcovado.Utils;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ComercioJsonController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private ProdutoVendidoRepository pvRepo;

    @GetMapping("/comercio/produto_json")
    public List<Produto> list_prod() {
        List<Produto> produtos = Utils.asList(prodRepo.findAll());
        produtos.forEach((produto) -> {
            // TODO criar uma melhor seleção de preços
            produto.setPreco( produto.getPrecos().get(0).getPreco() );
        });

        return produtos;
    }
    
    @GetMapping("/comercio/carrinho_json")
    public Venda list_cart() {
        return vendaRepo.findById(1L).get();
    }
    
    @PostMapping("/comercio/carrinho_json/add")
    public Venda addCart(@RequestParam(name = "produtoId") Long produtoId, 
            @RequestParam(name = "quantidade", required = false, defaultValue = "1") int quantidade) {
        Venda venda = vendaRepo.findById(1L).get();
        
        Produto produto;
        if(prodRepo.existsById(produtoId)) {
            produto = prodRepo.findById(produtoId).get();
            // TODO criar uma melhor seleção de preços
            produto.setPreco( produto.getPrecos().get(0).getPreco() );
        } else {
            return vendaRepo.findById(1L).get();
        }
        
        ProdutoVendido prodVend = venda.getProdutoVendidos()
                .stream()
                .filter((pv) -> { return pv.getProduto().equals(produto); })
                .findFirst().orElse(null);
        
        if (prodVend != null) {
            prodVend.setQuantidade(prodVend.getQuantidade() + quantidade);
            prodVend.setPrecoTotal(prodVend.getPrecoTotal() + (quantidade * produto.getPreco()));
        } else {
            prodVend = new ProdutoVendido();
            prodVend.setProduto(produto);
            prodVend.setVenda(venda);
            prodVend.setQuantidade(quantidade);
            prodVend.setPrecoTotal(quantidade * produto.getPreco());
        }
        
        pvRepo.save(prodVend);
        
        return vendaRepo.findById(1L).get();
    }
}
