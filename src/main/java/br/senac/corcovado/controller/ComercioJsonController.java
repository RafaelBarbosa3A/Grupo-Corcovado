package br.senac.corcovado.controller;


import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import br.senac.corcovado.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComercioJsonController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private VendaRepository vendaRepo;
    
    @GetMapping(value = "/comercio/produto_json")
    public Iterable<Produto> listProd() {
        Iterable<Produto> produtos = prodRepo.findAll();
        return produtos;
    }

    @GetMapping(value = "/comercio/carrinho_json")
    public Venda openCart() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            //TODO método especifico que busca apenas rascunhos.
            Venda venda = vendaRepo.findByPessoaAndStatus(pessoa, Status.RASCUNHO).orElse(new Venda());

            if(venda.getId() <= 0) {
                venda.setPessoa(pessoa);
            }

            return venda;
        } else {
            return new Venda();
        }
    }
    
    /*
    @GetMapping(value = "/comercio/carrinho_json/{id}")
    public Venda getCart(@PathVariable("id") long id) {
        return vendaRepo.findById(id).get();
    }
 
    @PostMapping(value = "/comercio/carrinho_json/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addCart(@RequestBody Carrinho cart) {
        Venda venda;
        if (cart.vendaId <= 0) {
            venda = new Venda();
        } else {
            venda = vendaRepo.findById(cart.vendaId).get();
        }

        venda.setProdutoVendidos(new HashSet<>());
        venda = vendaRepo.save(venda);

        for(ItemCarrinho item : cart.itens) {
            Produto produto = prodRepo.findById(item.produtoId).get();
            
            ProdutoVendido prodVend = new ProdutoVendido();
            prodVend.setProduto(produto);
            prodVend.setVenda(venda);
            prodVend.setQuantidade(item.quantidade);
            prodVend.setPrecoTotal(item.quantidade * produto.getPreco());
                
            /*
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
            * /

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

        //TODO processar o pagamento (utilizar o campo pedido cartao).
        venda.setStatus(Status.ENTREGA);
        venda.setComprovante(RandomStringUtils.randomAlphanumeric(20));
        venda.setCodigoRastreamento(RandomStringUtils.randomAlphanumeric(20));

        venda.calculaTotal();
        Venda salvo = vendaRepo.save(venda);

        return new ModelAndView("redirect:/comercio/carrinho_json/" + salvo.getId());
    }

    @GetMapping(value = "/comercio/pessoa_json")
    public Pessoa getPessoa() {
        Logger.getLogger(ComercioJsonController.class.getName()).log(Level.SEVERE, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()); 
        
        return pessRepo.findById(1L).get();
    }
    
    @GetMapping(value = "/comercio/pessoa_json/{id}")
    public Pessoa getPessoa(@PathVariable("id") long id) {        
        return pessRepo.findById(id).get();
    }

    @PostMapping(value = "/comercio/pessoa_json/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addPessoa(@RequestBody Pessoa pessoa) {
        pessoa.setNivel(Nivel.BASIC);
        pessoa.getPapeis().add(papelRepo.findById(1L).get());
        pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
                
        Pessoa salvo = pessRepo.save(pessoa);
        return new ModelAndView("redirect:/comercio/pessoa_json/" + salvo.getId());
    }
    */
}
