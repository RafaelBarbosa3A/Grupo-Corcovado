package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Cargo;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.exception.PessoaException;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.validator.PessoaValidador;
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
public class PessoaController {

    @Autowired 
    private PessoaRepository repository;
    
    @GetMapping("/pessoas")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("pessoa_list");
        mav.addObject("pessoas", repository.findAll());
        return mav;
    }
    
    @GetMapping("/pessoas/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("pessoa_show");
        mav.addObject("pessoa", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/pessoas/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/pessoas/create")
    public ModelAndView create(@ModelAttribute Pessoa pessoa) {
        Pessoa salvo;
        try {
            PessoaValidador.validar(pessoa);
            salvo = repository.save(pessoa);
        } catch (PessoaException ex) { 
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("pessoa", pessoa);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/pessoas/{id}/edit", "/pessoas/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/pessoas/update")
    public ModelAndView update(@ModelAttribute Pessoa pessoa) {
        Pessoa salvo;
        try {
            PessoaValidador.validar(pessoa);
            salvo = repository.save(pessoa); 
        } catch (PessoaException ex) { 
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(pessoa);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/pessoas/{id}/destroy", "/pessoas/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/pessoas");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("pessoa_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("pessoa", new Pessoa());
        modelAndView.addObject("niveis", Nivel.values());
        modelAndView.addObject("cargos", Cargo.values());
        return modelAndView;
    }
    
    private ModelAndView editForm(Pessoa pessoa) {
        ModelAndView modelAndView = new ModelAndView("pessoa_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("pessoa", pessoa);
        modelAndView.addObject("niveis", Nivel.values());
        modelAndView.addObject("cargos", Cargo.values());
        return modelAndView;
    }
}
