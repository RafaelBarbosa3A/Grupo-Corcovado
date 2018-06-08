package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PapelRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
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
public class PessoaController {
    @Autowired private PessoaRepository pessRepo;
    @Autowired private PapelRepository papelRepo;
    @Autowired private EnderecoRepository endRepo;

    @GetMapping("/pessoas")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/pessoa/pessoa_list");
        mav.addObject("pessoas", pessRepo.findAll());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @GetMapping("/pessoas/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        Pessoa pessoa = pessRepo.findById(id).get();
        
        return new ModelAndView("/pessoa/pessoa_show")
                .addObject("pessoa", pessoa);
                //.addObject("auth", Utils.getAuth());
    }

    @GetMapping("/pessoas/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }

    @PostMapping(path = "/pessoas/create")
    public ModelAndView create(@ModelAttribute Pessoa pessoa) {
        Pessoa salvo = pessRepo.save(pessoa);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }

    @GetMapping({"/pessoas/{id}/edit", "/pessoas/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView mav = editForm(pessRepo.findById(id).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @PostMapping(path = "/pessoas/update")
    public ModelAndView update(@ModelAttribute Pessoa pessoa) {
    	pessoa.setEnderecos(Utils.asSet(endRepo.findAllByPessoaId(pessoa.getId())));
        Pessoa salvo = pessRepo.save(pessoa);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }

    @PostMapping(path = {"/pessoas/{id}/destroy", "/pessoas/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") long id) {
        pessRepo.deleteById(id);
        ModelAndView redirect = new ModelAndView("redirect:/pessoas");
        return redirect;
    }

    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("/pessoa/pessoa_form");
        modelAndView.addObject("action", "create")
                .addObject("pessoa", new Pessoa())
                .addObject("papeis", papelRepo.findAll());
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }

    private ModelAndView editForm(Pessoa pessoa) {
        ModelAndView modelAndView = new ModelAndView("/pessoa/pessoa_form")
                .addObject("action", "update")
                .addObject("pessoa", pessoa)
                .addObject("papeis", papelRepo.findAll());
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }
    
        
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
