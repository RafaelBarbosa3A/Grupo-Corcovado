package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
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

    @Autowired private ProdutoRepository repository;
    @Autowired private DepartamentoRepository deptoRepo;
    
    
    
    @GetMapping("/produtos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/produto/produto_list");
        mav.addObject("produtos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/produtos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/produto/produto_show");
        mav.addObject("produto", repository.findProdutoById(Long.parseLong(usId)).get());
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

        //TODO implementar validador via @Valid
        salvo = repository.save(produto);
        
        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/produtos/{id}/edit", "/produtos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findProdutoById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/produtos/update")
    public ModelAndView update(@ModelAttribute Produto produto) {
        Produto salvo;

        //TODO implementar validador via @Valid
        salvo = repository.save(produto);
        
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
        modelAndView.addObject("departamentos", deptoRepo.findAll());
        //modelAndView.addObject("categorias", categoriaRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("/produto/produto_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("departamentos", deptoRepo.findAll());
        //modelAndView.addObject("categorias", categoriaRepository.findAll());
        return modelAndView;
    }
}
