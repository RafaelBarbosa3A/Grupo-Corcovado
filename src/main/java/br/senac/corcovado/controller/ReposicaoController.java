package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Reposicao;
import br.senac.corcovado.model.exception.ReposicaoException;
import br.senac.corcovado.model.repository.ReposicaoRepository;
import br.senac.corcovado.model.validator.ReposicaoValidador;
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
public class ReposicaoController {

    @Autowired 
    private ReposicaoRepository repository;
    
    @GetMapping("/reposicoes")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("reposicao_list");
        mav.addObject("reposicoes", repository.findAll());
        return mav;
    }
    
    @GetMapping("/reposicoes/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("reposicao_show");
        mav.addObject("reposicoes", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/reposicoes/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/reposicoes/create")
    public ModelAndView create(@ModelAttribute Reposicao reposicao) {
        Reposicao salvo;
        try {
            ReposicaoValidador.validar(reposicao);
            salvo = repository.save(reposicao);
        } catch (ReposicaoException ex) { 
            Logger.getLogger(ReposicaoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("reposicao", reposicao);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/reposicoes/{id}/edit", "/reposicoes/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/reposicoes/update")
    public ModelAndView update(@ModelAttribute Reposicao reposicao) {
        Reposicao salvo;
        try {
            ReposicaoValidador.validar(reposicao);
            salvo = repository.save(reposicao);
        } catch (ReposicaoException ex) { 
            Logger.getLogger(ReposicaoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(reposicao);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/reposicoes/{id}/destroy", "/reposicoes/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/reposicoes");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("reposicao_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("reposicao", new Reposicao());        
        return modelAndView;
    }
    
    private ModelAndView editForm(Reposicao reposicao) {
        ModelAndView modelAndView = new ModelAndView("reposicao_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("reposicao", reposicao);        
        return modelAndView;
    }
}
