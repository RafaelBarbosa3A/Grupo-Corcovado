package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.exception.AvisoException;
import br.senac.corcovado.model.repository.AvisoRepository;
import br.senac.corcovado.model.validator.AvisoValidador;
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
public class AvisoController {
    
    @Autowired 
    private AvisoRepository repository;
    
    @GetMapping("/avisos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("aviso_list");
        mav.addObject("avisos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/avisos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("aviso_show");
        mav.addObject("aviso", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/avisos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/avisos/create")
    public ModelAndView create(@ModelAttribute Aviso aviso) {
        Aviso salvo;
        try {
            AvisoValidador.validar(aviso);
            salvo = repository.save(aviso);
        } catch (AvisoException ex) { 
            Logger.getLogger(AvisoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("aviso", aviso);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/avisos/{id}/edit", "/avisos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/avisos/update")
    public ModelAndView update(@ModelAttribute Aviso aviso) {
        Aviso salvo;
        try {
            AvisoValidador.validar(aviso);
            salvo = repository.save(aviso); 
        } catch (AvisoException ex) { 
            Logger.getLogger(AvisoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(aviso);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/avisos/{id}/destroy", "/avisos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/avisos");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("aviso_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("aviso", new Aviso());        
        return modelAndView;
    }
    
    private ModelAndView editForm(Aviso aviso) {
        ModelAndView modelAndView = new ModelAndView("aviso_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("aviso", aviso);        
        return modelAndView;
    }
}
