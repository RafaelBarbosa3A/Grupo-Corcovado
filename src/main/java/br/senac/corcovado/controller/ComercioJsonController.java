package br.senac.corcovado.controller;

import br.senac.corcovado.controller.adapter.Carrinho;
import br.senac.corcovado.controller.adapter.ItemCarrinho;
import br.senac.corcovado.controller.adapter.Pedido;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ComercioJsonController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private ProdutoVendidoRepository pvRepo;
    @Autowired private PessoaRepository pessRepo;

    @GetMapping(value = "/comercio/produto_json")
    public Iterable<Produto> listProd() {
        Iterable<Produto> produtos = prodRepo.findAll();
        produtos.forEach((p) -> { p.setPreco(p.getPrecos().get(0).getPreco()); });
        return produtos;
    }

    @GetMapping(value = "/comercio/carrinho_json/{id}")
    public Venda getCart(@PathVariable("id") long id) {
        return vendaRepo.findById(id).get();
    }

    @GetMapping(value = "/comercio/carrinho_json")
    public Venda openCart() {
        // get from the session of Spring Security
        Pessoa pessoa = pessRepo.findById(1L).get();

        //statusId = 1 => RASCUNHO
        //TODO mudar para passar o enum RASCUNHO, e/ou método especifico que busca apenas rascunhos.
        
        Venda venda = vendaRepo.findByPessoaAndStatusId(pessoa, 1).orElse(new Venda());
        
        if(venda.getId() <= 0) {
            venda.setPessoa(pessoa);
            venda = vendaRepo.save(venda);
        }
        
        return venda;
    }

    @PostMapping(value = "/comercio/carrinho_json/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addCart(@RequestBody Carrinho cart) {
        Venda venda;
        /*
        if (cart.vendaId <= 0) {
            venda = new Venda();
        } else {
        */
            venda = vendaRepo.findById(cart.vendaId).get();
        //}

        venda = vendaRepo.save(venda);

        for(ItemCarrinho item : cart.itens) {
            ProdutoVendido prodVend = venda.getProdutoVendidos()
                    .stream()
                    .filter((pv) -> { return pv.getProduto().getId() == item.produtoId; })
                    .findFirst().orElse(null);

            if (prodVend != null) {
                prodVend.setQuantidade(prodVend.getQuantidade() + item.quantidade);
                prodVend.setPrecoTotal(prodVend.getPrecoTotal() + (item.quantidade * prodVend.getProduto().getPreco()));
            } else {
                Produto produto = prodRepo.findById(item.produtoId).get();

                prodVend = new ProdutoVendido();
                prodVend.setProduto(produto);
                prodVend.setVenda(venda);
                prodVend.setQuantidade(item.quantidade);
                prodVend.setPrecoTotal(item.quantidade * produto.getPreco());
            }

            venda.getProdutoVendidos().add(prodVend);
            pvRepo.save(prodVend);
        }
        venda.calculaTotal();
        Venda salvo = vendaRepo.save(venda);

        return new ModelAndView("redirect:/comercio/carrinho_json/" + salvo.getId());
    }

    @PostMapping(value = "/comercio/venda_json/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addPedido(@RequestBody Pedido pedido) {
        Venda venda;
        if (pedido.vendaId == 0) {
            venda = new Venda();
        } else {
            venda = vendaRepo.findById(pedido.vendaId).get();
        }
        venda.setPessoa(pessRepo.findById(pedido.pessoaId).get());
        venda.setEnderecoId(pedido.enderecoId);
        venda.setFrete(pedido.frete);
        venda.setPagamento(pedido.pagamento);

        //TODO processar o pagamento (utilizar o campo pedido.cartao).

        venda.setStatusId(Status.ENTREGA.getId());
        
        venda.setComprovante(RandomStringUtils.randomAlphanumeric(20));
        venda.setCodigoRastreamento(RandomStringUtils.randomAlphanumeric(20));

        venda.calculaTotal();
        Venda salvo = vendaRepo.save(venda);

        return new ModelAndView("redirect:/comercio/carrinho_json/" + salvo.getId());
    }

    @GetMapping(value = "/comercio/pessoa_json")
    public Pessoa getPessoa() {
        return pessRepo.findById(1L).get();
    }

}
