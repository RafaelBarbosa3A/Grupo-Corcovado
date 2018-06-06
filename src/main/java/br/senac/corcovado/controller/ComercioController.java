/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.controller.adapter.Carrinho;
import br.senac.corcovado.controller.adapter.ItemCarrinho;
import br.senac.corcovado.controller.adapter.Pedido;
import br.senac.corcovado.model.entity.Papel;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diego
 */
@Controller
public class ComercioController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private PessoaRepository pessRepo;
    @Autowired private ProdutoVendidoRepository pvRepo;
    
    @GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("redirect:/comercio");
    }
    
    
    @GetMapping("/comercio")
    public ModelAndView main() {
        // TODO adicionar paramtros de busca
        
        return new ModelAndView("/comercio/comercio")
                .addObject("auth", Utils.getAuth());
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
        Carrinho cart = new Carrinho();
        cart.vendaId = 1L;
        return new ModelAndView("/comercio/_cart").addObject("carrinho", cart);
    }
    
    /*
    @GetMapping("/comercio/finaliza")
    public ModelAndView finaliza() {
        return new ModelAndView("/comercio/_finaliza");
    }
    */
    

    @PostMapping("/comercio/carrinho/add")
    public ModelAndView createCarrinho(@ModelAttribute("carrinho") Carrinho cart) {
        Pessoa pessoa = (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Venda venda;
        if (cart.vendaId <= 0) {
            venda = vendaRepo.findByPessoaAndStatus(pessoa, Status.RASCUNHO).orElse(new Venda());
        } else {
            venda = vendaRepo.findById(cart.vendaId).get();
        }
        
        venda.setPessoa(pessoa);
        
        // Descarta carrinho velho
        venda.getProdutoVendidos().clear();
        venda = vendaRepo.save(venda);
        pvRepo.deleteAll(pvRepo.findByVendaId(venda.getId()));

        for(ItemCarrinho item : cart.itens) {
            Produto produto = prodRepo.findById(item.produtoId).get();
            
            ProdutoVendido prodVend = new ProdutoVendido();
            prodVend.setProduto(produto);
            prodVend.setVenda(venda);
            prodVend.setQuantidade(item.quantidade);
            prodVend.setPrecoTotal(item.quantidade * produto.getPreco());

            venda.getProdutoVendidos().add(prodVend);
            pvRepo.save(prodVend);
        }
        venda.calculaTotal();
        Venda salvo = vendaRepo.save(venda);

        return new ModelAndView("redirect:/comercio/carrinho/finaliza/" + salvo.getId());
    }
    
    @GetMapping("/comercio/carrinho/finaliza/{id}")
    public ModelAndView finalizaCarrinho(@PathVariable("id") long id) {
        Venda venda = vendaRepo.findById(id).get();
        
        Pedido pedido = new Pedido();
        
        pedido.setVendaId(venda.getId());
        pedido.setPessoaId(venda.getPessoa().getId());
        
        return new ModelAndView("/comercio/finaliza")
                .addObject("pedido", pedido)
                .addObject("venda", venda)
                .addObject("auth", Utils.getAuth());
    }
    
    @PostMapping("/comercio/venda/add")
    public ModelAndView createVenda(@ModelAttribute Pedido pedido) {
        Venda venda = vendaRepo.findById(pedido.getVendaId()).get();
        
        venda.setEnderecoId(pedido.getEnderecoId());
        venda.setFrete(pedido.getFrete());
        venda.setPagamento(pedido.getPagamento());
        
        venda.calculaTotal();
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        venda.setPrazoEntrega(cal.getTime());
        venda.setCodigoRastreamento(RandomStringUtils.randomAlphanumeric(12,25));
        venda.setComprovante(RandomStringUtils.randomAlphanumeric(12,25));
        venda.setStatus(Status.ENTREGA);
        
        venda = vendaRepo.save(venda);
        
        return new ModelAndView("redirect:/comercio/recibo/" + venda.getId());
    }
    
    @GetMapping("/comercio/recibo/{id}")
    public ModelAndView recibo(@PathVariable("id") long id) {
        // Apenas permitr ver os pedidos do usuário logado.
        
        Object principal = (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Venda venda = vendaRepo.findById(id).get();
        if (principal instanceof Pessoa && venda.getPessoa().getId().equals(((Pessoa) principal).getId())) {
            return new ModelAndView("/comercio/recibo")
                    .addObject("venda", venda)
                    .addObject("auth", Utils.getAuth());
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
}
