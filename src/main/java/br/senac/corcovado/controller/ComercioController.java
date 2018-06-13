/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;


import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.controller.adapter.Carrinho;
import br.senac.corcovado.controller.adapter.ItemCarrinho;
import br.senac.corcovado.controller.adapter.Pedido;
import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.entity.Pagamento;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PapelRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import br.senac.corcovado.model.service.AuthService;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Diego
 */
@Controller
public class ComercioController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    @Autowired private CategoriaRepository cateRepo;
    @Autowired private ProdutoVendidoRepository pvRepo;
    @Autowired private EnderecoRepository endRepo;
    @Autowired private PessoaRepository pessRepo;
    @Autowired private PapelRepository papelRepo;
    
    @GetMapping({"", "index", "home"})
    public ModelAndView index() {
        return new ModelAndView("redirect:/comercio");
    }
    
    
    @GetMapping("/comercio")
    public ModelAndView main() {
        // TODO adicionar parametros de busca
        return new ModelAndView("/comercio/comercio");
    }
    
    @GetMapping("/comercio/list")
    public ModelAndView list() {
        return new ModelAndView("/comercio/_list")
                .addObject("categorias", cateRepo.findAll());
    }
    
    @GetMapping("/comercio/home")
    public ModelAndView home() {
        return new ModelAndView("/comercio/_home");
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
            prodVend.calculaPrecoTotal();

            venda.getProdutoVendidos().add(prodVend);
            pvRepo.save(prodVend);
            
            produto.setReservado(produto.getReservado() + item.quantidade);
            prodRepo.save(produto);
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
        
        for(ProdutoVendido pv : venda.getProdutoVendidos()) {
            Produto produto = pv.getProduto();
            produto.setEstoque(produto.getEstoque() - pv.getQuantidade());
            prodRepo.save(produto);
        }
        
        return new ModelAndView("/comercio/finaliza")
                .addObject("pedido", pedido)
                .addObject("venda", venda)
                .addObject("pagamentos", Pagamento.values());
    }
    
    @PostMapping("/comercio/venda/add")
    public ModelAndView createVenda(@ModelAttribute Pedido pedido) {
        Venda venda = vendaRepo.findById(pedido.getVendaId()).get();
        
        venda.setEndereco(endRepo.findById(pedido.getEnderecoId()).get());
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
        Venda venda = vendaRepo.findById(id).get();
        if (venda.getPessoa().getId().equals(getCurrentUser().getId())) {
            return new ModelAndView("/comercio/recibo")
                    .addObject("venda", venda);
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    @GetMapping("/comercio/compras")
    public ModelAndView compras() {
        Pessoa pessoa = getCurrentUser();

        return new ModelAndView("/comercio/compras")
                .addObject("vendas", vendaRepo.findAllByPessoaId(pessoa.getId()));
    }
    
    
    @GetMapping("/comercio/perfil/edit")
    public ModelAndView editPerfil() {
        Pessoa pessoa = getCurrentUser();
        return perfilForm().addObject("pessoa", pessoa);
    }

    @PostMapping("/comercio/perfil/update")
    public ModelAndView updatePerfil(@Valid @ModelAttribute Pessoa pessoa, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Logger.getGlobal().log(Level.SEVERE, bindingResult.getFieldErrors().toString());
            return perfilForm();
        }
        Pessoa oldPessoa = getCurrentUser();
        oldPessoa.setNome(pessoa.getNome());
        oldPessoa.setDocumento(pessoa.getDocumento());
        
        Pessoa salvo = pessRepo.save(oldPessoa);

        return new ModelAndView("redirect:/");
    }
    
    private ModelAndView perfilForm() {
        return new ModelAndView("/comercio/pessoa_form");
    }
    
    
    @GetMapping("/comercio/enderecos")
    public ModelAndView listEndereco() {
        Pessoa pessoa = getCurrentUser();

        return new ModelAndView("/comercio/endereco_list")
                .addObject("enderecos", endRepo.findAllByPessoaId(pessoa.getId()));
    }
    
    @GetMapping("/comercio/enderecos/new")
    public ModelAndView new_() {
        Endereco endereco = new Endereco();
        endereco.setPessoa(getCurrentUser());
        
        return enderecoForm("create").addObject("endereco", endereco);
    }
    
    @PostMapping(path = "/comercio/enderecos/create")
    public ModelAndView create(@Valid @ModelAttribute Endereco endereco, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return enderecoForm("create");
        }
        endereco.setPessoa(getCurrentUser());
        
        endRepo.save(endereco);
        //redirectAttributes.addFlashAttribute("mensagem", "Endereço cadastrado com sucesso!!!");
        return new ModelAndView("redirect:/comercio/enderecos");
    }
    
    @GetMapping({"/comercio/enderecos/{id}/edit", "/comercio/enderecos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") long id) {
        Endereco endereco =  endRepo.findById(id).get();
        if (endereco.getPessoa().getId().equals(getCurrentUser().getId())) {
            return enderecoForm("update")
                    .addObject("endereco", endereco);
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    @PostMapping(path = "/comercio/enderecos/update")
    public ModelAndView update(@Valid @ModelAttribute Endereco endereco, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return enderecoForm("update");
        }
        endereco.setPessoa(getCurrentUser());
        
        endRepo.save(endereco);
        //redirectAttributes.addFlashAttribute("mensagem", "Endereço alterado com sucesso!!!");
        return new ModelAndView("redirect:/comercio/enderecos");
    }
    
    @PostMapping(path = {"/comercio/enderecos/{id}/destroy", "/comercio/enderecos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") long id) {
        if (endRepo.existsById(id) && endRepo.findById(id).get().getPessoa().getId().equals(getCurrentUser().getId())) {
            endRepo.deleteById(id);
            return new ModelAndView("redirect:/comercio/enderecos");
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    private ModelAndView enderecoForm(String action) {
        return new ModelAndView("/comercio/endereco_form")
            .addObject("action", action);
    }
    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
    
    private Pessoa getCurrentUser() throws AuthenticationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Pessoa) {
            return (Pessoa) principal;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Você não está devidamente logado");
        }
    }
}
