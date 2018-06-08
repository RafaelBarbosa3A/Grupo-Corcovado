package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Resposta;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.RespostaRepository;
import br.senac.corcovado.model.repository.SacRepository;
import br.senac.corcovado.model.service.AuthService;
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
public class RespostaController {
    
    @Autowired 
    private RespostaRepository repository;
    private PessoaRepository pessoaRepository;
    private SacRepository sacRepository;

    @GetMapping("/respostas")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/resposta/resposta_list");
        mav.addObject("respostas", repository.findAll());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping("/respostas/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/resposta/resposta_show");
        mav.addObject("resposta", repository.findById(Long.parseLong(usId)).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping("/respostas/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/respostas/create")
    public ModelAndView create(@ModelAttribute Resposta resposta) {
        Resposta salvo;
        salvo = repository.save(resposta);
        
        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/respostas/{id}/edit", "/respostas/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @PostMapping(path = "/respostas/update")
    public ModelAndView update(@ModelAttribute Resposta resposta) {
        Resposta salvo;
        salvo = repository.save(resposta);
        
        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/respostas/{id}/destroy", "/respostas/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/respostas");
        return redirect;
    }
    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/resposta/resposta_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("resposta", new Resposta());
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("sacs", sacRepository.findAll());
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }
    
    private ModelAndView editForm(Resposta resposta) {
        ModelAndView modelAndView = new ModelAndView("/resposta/resposta_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("resposta", resposta);
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("sacs", sacRepository.findAll());
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }
    
            
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
