package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.exception.ProdutoVendidoException;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoaRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import br.senac.corcovado.model.validator.ProdutoVendidoValidador;
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
public class ProdutoVendidoController {

    @Autowired 
    private ProdutoVendidoaRepository repository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;
    
    @GetMapping("/produtos_vendidos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("produto_vendido_list");
        mav.addObject("produtos_vendidos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/produtos_vendidos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("produto_vendido_show");
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
        try {
            ProdutoVendidoValidador.validar(produto_vendido);
            salvo = repository.save(produto_vendido);
        } catch (ProdutoVendidoException ex) { 
            Logger.getLogger(ProdutoVendidoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("produto_vendido", produto_vendido);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

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
        try {
            ProdutoVendidoValidador.validar(produto_vendido);
            salvo = repository.save(produto_vendido);
        } catch (ProdutoVendidoException ex) { 
            Logger.getLogger(ProdutoVendidoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(produto_vendido);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

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
        ModelAndView modelAndView = new ModelAndView("produto_vendido_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("produtos_vendidos", produto_vendido);
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("vendas", vendaRepository.findAll());
        return modelAndView;
    }
}
