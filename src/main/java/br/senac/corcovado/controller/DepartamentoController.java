package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.exception.DepartamentoException;
import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.validator.DepartamentoValidador;
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
public class DepartamentoController {

    @Autowired 
    private DepartamentoRepository repository;
    
    @GetMapping("/departamentos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/departamento/departamento_list");
        mav.addObject("departamentos", repository.findAll());
        return mav;
    }
    
    @GetMapping("/departamentos/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/departamento/departamento_show");
        mav.addObject("departamento", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/departamentos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/departamentos/create")
    public ModelAndView create(@ModelAttribute Departamento departamento) {
        Departamento salvo;
        try {
            DepartamentoValidador.validar(departamento);
            salvo = repository.save(departamento);
        } catch (DepartamentoException ex) { 
            Logger.getLogger(DepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("departamento", departamento);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/departamentos/{id}/edit", "/departamentos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/departamentos/update")
    public ModelAndView update(@ModelAttribute Departamento departamento) {
        Departamento salvo;
        try {
            DepartamentoValidador.validar(departamento);
            salvo = repository.save(departamento); 
        } catch (DepartamentoException ex) { 
            Logger.getLogger(DepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(departamento);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/departamentos/{id}/destroy", "/departamentos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/departamentos");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("departamento/departamento_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("departamento", new Departamento());        
        return modelAndView;
    }
    
    private ModelAndView editForm(Departamento departamento) {
        ModelAndView modelAndView = new ModelAndView("departamento/departamento_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("departamento", departamento);        
        return modelAndView;
    }
}
