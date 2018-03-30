package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.SAC;
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

    @GetMapping("/sacs")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("sacs_list");
        mav.addObject("sacs", repository.findAll());
        return mav;
    }
    
    @GetMapping("/sacs/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("sac_show");
        mav.addObject("sac", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/sacs/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/sacs/create")
    public ModelAndView create(@ModelAttribute SAC sac) {
        SAC salvo;
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
    
    @GetMapping({"/sacs/{id}/edit", "/sacs/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/sacs/update")
    public ModelAndView update(@ModelAttribute SAC sac) {
        SAC salvo;
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
    
    @PostMapping(path = {"/sacs/{id}/destroy", "/sacs/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/sacs");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("sac_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("sac", new SAC());
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(SAC sac) {
        ModelAndView modelAndView = new ModelAndView("sac_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("sac", sac);
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        return modelAndView;
    }
}
