package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Sac;
import br.senac.corcovado.model.exception.SACException;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.SacRepository;
import br.senac.corcovado.model.validator.SACValidador;
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
public class SACController {
    
    @Autowired 
    private SacRepository repository;
    private PessoaRepository pessoaRepository;

    @GetMapping("/sac")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/sac/sac_list");
        mav.addObject("sac", repository.findAll());
        return mav;
    }
    
    @GetMapping("/sac/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/sac/sac_show");
        mav.addObject("sac", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/sac/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/sac/create")
    public ModelAndView create(@ModelAttribute Sac sac) {
        Sac salvo;
        try {
            SACValidador.validar(sac);
            salvo = repository.save(sac);
        } catch (SACException ex) { 
            Logger.getLogger(SACController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("sac", sac);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/sac/{id}/edit", "/sac/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/sac/update")
    public ModelAndView update(@ModelAttribute Sac sac) {
        Sac salvo;
        try {
            SACValidador.validar(sac);
            salvo = repository.save(sac);
        } catch (SACException ex) { 
            Logger.getLogger(SACController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(sac);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/sac/{id}/destroy", "/sac/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/sac");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/sac/sac_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("sac", new Sac());
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(Sac sac) {
        ModelAndView modelAndView = new ModelAndView("/sac/sac_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("sac", sac);
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        return modelAndView;
    }
}
