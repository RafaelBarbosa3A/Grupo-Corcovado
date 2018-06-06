package br.senac.corcovado.controller;


import br.senac.corcovado.Utils;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wesley
 */
@Controller
@RequestMapping
public class PrecoController {
    @Autowired private PrecoRepository precoRepo;
    @Autowired private ProdutoRepository prodRepo;
    
    @GetMapping("/produtos/{produtoId}/precos")
    public ModelAndView list(@PathVariable("produtoId") long produtoId) {
        Produto produto = prodRepo.findById(produtoId).get();
        //produto.setPrecos(Utils.asList(precoRepo.findAllByProdutoId(produtoId)));
        
        return new ModelAndView("/preco/preco_list")
                .addObject("produto", produto)
                .addObject("auth", Utils.getAuth())
                //.addObject("precos", produto.getPrecos())
                //.addObject("novoPreco", Nivel.values().length > produto.getPrecos().size())
        ;
    }
    
    @GetMapping("/produtos/precos/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        return new ModelAndView("/preco/preco_show")
                .addObject("preco", precoRepo.findById(id).get())
                .addObject("auth", Utils.getAuth());
    }
    
    @GetMapping("/produtos/{produtoId}/precos/new")
    public ModelAndView new_(@PathVariable("produtoId") long produtoId) {
        Produto produto = prodRepo.findById(produtoId).get();
        //List<Preco> precos = Utils.asList(precoRepo.findAllByProdutoId(produtoId));
        
        Preco preco = new Preco();
        preco.setProduto(produto);
        
        ArrayList<Nivel> niveis = Utils.asList(Nivel.values());
        /*
        ArrayList<Nivel> niveis = new ArrayList<>();
        for (Nivel nivel : Nivel.values()) {
            niveis.add(nivel);
        }
        * /
        
        List<Nivel> niveisUtilizados = precos.stream().map(Preco::getNivel).collect(Collectors.toList());
        niveis.removeAll(niveisUtilizados);
        /**/
        return new ModelAndView("/preco/preco_form")
                .addObject("action", "create")
                .addObject("preco", preco)
                .addObject("niveis", niveis)
                .addObject("auth", Utils.getAuth());
    }

    @GetMapping({"/produtos/precos/{id}/edit", "/produtos/precos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") long id) {
        Preco preco = precoRepo.findById(id).get();
        
        return new ModelAndView("/preco/preco_form")
                .addObject("preco", preco)
                .addObject("action", "update")
                .addObject("auth", Utils.getAuth());
    }

    @PostMapping(path = {"/produtos/precos/create", "/produtos/precos/update"})
    public ModelAndView save(@ModelAttribute Preco preco) {
        Preco salvo;
        
        //TODO implementar validador via @Valid
        salvo = precoRepo.save(preco);
        
        return new ModelAndView("redirect:/produtos/precos/" + salvo.getId());
    }
    
    @PostMapping(path = {"/produtos/precos/{id}/destroy", "/produtos/precos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") Long id) {
        Produto produto = precoRepo.findById(id).get().getProduto();
        precoRepo.deleteById(id);
        return new ModelAndView("redirect:/produtos/" + produto.getId() + "/precos");
    }
}