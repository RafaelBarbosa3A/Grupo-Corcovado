package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.exception.ProdutoVendidoException;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;

/**
 *
 * @author wesley
 */
@Controller
public class ProdutoVendidoController {

    @Autowired 
    private ProdutoVendidoRepository repository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;
    
    @GetMapping("/produtos_vendidos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/produto_vendido/produto_vendido_list");
        mav.addObject("produtos_vendidos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/produtos_vendidos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/produto_vendido/produto_vendido_show");
        mav.addObject("produto_vendido", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/produtos_vendidos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/produtos_vendidos/create")
    public ModelAndView create(@ModelAttribute ProdutoVendido produto_vendido) {
        ProdutoVendido salvo;
            salvo = repository.save(produto_vendido);
        

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/produtos_vendidos/{id}/edit", "/produtos_vendidos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/produtos_vendidos/update")
    public ModelAndView update(@ModelAttribute ProdutoVendido produto_vendido) {
        ProdutoVendido salvo;
            salvo = repository.save(produto_vendido);
        

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/produtos_vendidos/{id}/destroy", "/produtos_vendidos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/produtos_vendidos");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("produto_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("produtos_vendidos", new ProdutoVendido());
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("vendas", vendaRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(ProdutoVendido produto_vendido) {
        ModelAndView modelAndView = new ModelAndView("/produto_vendido/produto_vendido_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("produtos_vendidos", produto_vendido);
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("vendas", vendaRepository.findAll());
        return modelAndView;
    }
}
