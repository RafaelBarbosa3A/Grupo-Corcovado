package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.exception.EnderecoException;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.validator.EnderecoValidador;
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
public class EnderecoController {

    @Autowired 
    private EnderecoRepository repository;
    
    @GetMapping("/enderecos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("endereco_list");
        mav.addObject("enderecos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/enderecos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("endereco_show");
        mav.addObject("endereco", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/enderecos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/enderecos/create")
    public ModelAndView create(@ModelAttribute Endereco endereco) {
        Endereco salvo;
        try {
            EnderecoValidador.validar(endereco);
            salvo = repository.save(endereco);
        } catch (EnderecoException ex) { 
            Logger.getLogger(EnderecoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("endereco", endereco);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/enderecos/{id}/edit", "/enderecos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/enderecos/update")
    public ModelAndView update(@ModelAttribute Endereco endereco) {
        Endereco salvo;
        try {
            EnderecoValidador.validar(endereco);
            salvo = repository.save(endereco);
        } catch (EnderecoException ex) { 
            Logger.getLogger(EnderecoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(endereco);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/enderecos/{id}/destroy", "/enderecos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/enderecos");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("endereco_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("endereco", new Endereco());        
        return modelAndView;
    }
    
    private ModelAndView editForm(Endereco endereco) {
        ModelAndView modelAndView = new ModelAndView("endereco_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("endereco", endereco);        
        return modelAndView;
    }
}
