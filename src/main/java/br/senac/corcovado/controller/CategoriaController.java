package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.exception.CategoriaException;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.validator.CategoriaValidador;
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
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;
    
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    @GetMapping("/categorias")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/categoria/categoria_list");
        mav.addObject("categorias", repository.findAll());
        return mav;
    }
    
    @GetMapping("/categorias/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/categoria/categoria_show");
        mav.addObject("categoria", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/categorias/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/categorias/create")
    public ModelAndView create(@ModelAttribute Categoria categoria) {
        Categoria salvo;
        try {
            CategoriaValidador.validar(categoria);
            salvo = repository.save(categoria);
        } catch (CategoriaException ex) { 
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("categoria", categoria);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/categorias/{id}/edit", "/categorias/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/categorias/update")
    public ModelAndView update(@ModelAttribute Categoria categoria) {
        Categoria salvo;
        try {
            CategoriaValidador.validar(categoria);
            salvo = repository.save(categoria); 
        } catch (CategoriaException ex) { 
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(categoria);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/categorias/{id}/destroy", "/categorias/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/categorias");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/categoria/categoria_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("categoria", new Categoria());
        modelAndView.addObject("departamentos", departamentoRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(Categoria categoria) {
        ModelAndView modelAndView = new ModelAndView("/categoria/categoria_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("categoria", categoria);
        modelAndView.addObject("departamentos", departamentoRepository.findAll());        
        return modelAndView;
    }
}
