package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.exception.ProdutoException;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.validator.ProdutoValidador;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wesley
 */
@Controller
public class ProdutoController {

    @Autowired 
    private ProdutoRepository repository;
    private CategoriaRepository categoriaRepository;
    
    @GetMapping("/produtos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/produto/produto_list");
        mav.addObject("produtos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/produtos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/produto/produto_show");
        mav.addObject("produto", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/produtos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/produtos/create")
    public ModelAndView create(@ModelAttribute Produto produto) {
        Produto salvo;
        try {
            ProdutoValidador.validar(produto);
            salvo = repository.save(produto);
        } catch (ProdutoException ex) { 
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("produto", produto);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/produtos/{id}/edit", "/produtos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/produtos/update")
    public ModelAndView update(@ModelAttribute Produto produto) {
        Produto salvo;
        try {
            ProdutoValidador.validar(produto);
            salvo = repository.save(produto);
        } catch (ProdutoException ex) { 
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(produto);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/produtos/{id}/destroy", "/produtos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/produtos");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/produto/produto_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("produto", new Produto());
        modelAndView.addObject("categorias", categoriaRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("/produto/produto_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("categorias", categoriaRepository.findAll());
        return modelAndView;
    }
}
