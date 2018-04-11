package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Desconto;
import br.senac.corcovado.model.exception.DescontoException;
import br.senac.corcovado.model.repository.DescontoRepository;
import br.senac.corcovado.model.validator.DescontoValidador;
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
public class DescontoController {

    @Autowired 
    private DescontoRepository repository;
    
    @GetMapping("/descontos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/desconto/desconto_list");
        mav.addObject("descontos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/descontos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/desconto/desconto_show");
        mav.addObject("desconto", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/descontos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/descontos/create")
    public ModelAndView create(@ModelAttribute Desconto desconto) {
        Desconto salvo;
        try {
            DescontoValidador.validar(desconto);
            salvo = repository.save(desconto);
        } catch (DescontoException ex) { 
            Logger.getLogger(DescontoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("desconto", desconto);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/descontos/{id}/edit", "/descontos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/descontos/update")
    public ModelAndView update(@ModelAttribute Desconto desconto) {
        Desconto salvo;
        try {
            DescontoValidador.validar(desconto);
            salvo = repository.save(desconto);
        } catch (DescontoException ex) { 
            Logger.getLogger(DescontoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(desconto);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/descontos/{id}/destroy", "/descontos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/descontos");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/desconto/desconto_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("desconto", new Desconto());        
        return modelAndView;
    }
    
    private ModelAndView editForm(Desconto desconto) {
        ModelAndView modelAndView = new ModelAndView("/desconto/desconto_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("desconto", desconto);        
        return modelAndView;
    }
}
