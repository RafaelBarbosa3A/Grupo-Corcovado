package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.exception.PrecoException;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.validator.PrecoValidador;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/precos")
public class PrecoController {

    @Autowired 
    private PrecoRepository repository;
    private ProdutoRepository produtosRepository;
    
    @GetMapping("/precos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("preco_list");
        mav.addObject("precos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/precos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("preco_show");
        mav.addObject("preco", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/precos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/precos/create")
    public ModelAndView create(@ModelAttribute Preco preco) {
        Preco salvo;
        try {
            PrecoValidador.validar(preco);
            salvo = repository.save(preco);
        } catch (PrecoException ex) { 
            Logger.getLogger(PrecoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("preco", preco);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/precos/{id}/edit", "/precos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/precos/update")
    public ModelAndView update(@ModelAttribute Preco preco) {
        Preco salvo;
        try {
            PrecoValidador.validar(preco);
            salvo = repository.save(preco);
        } catch (PrecoException ex) { 
            Logger.getLogger(PrecoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(preco);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/precos/{id}/destroy", "/precos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/precos");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("preco_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("preco", new Preco());
        modelAndView.addObject("niveis", Nivel.values());
        modelAndView.addObject("produtos", produtosRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(Preco preco) {
        ModelAndView modelAndView = new ModelAndView("preco_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("preco", preco);
        modelAndView.addObject("niveis", Nivel.values());
        modelAndView.addObject("produtos", produtosRepository.findAll());
        return modelAndView;
    }
}
