package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.repository.EnderecoRepository;
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
public class EnderecoController {
    @Autowired private EnderecoRepository endRepo;
    @Autowired private PessoaRepository pesRepo;
    
    @GetMapping("/pessoas/{pessoaId}/enderecos")
    public ModelAndView list(@PathVariable("pessoaId") long pessoaId) {
        return new ModelAndView("/endereco/endereco_list")
                .addObject("pessoa", pesRepo.findById(pessoaId).get())
                .addObject("enderecos", endRepo.findAllByPessoaId(pessoaId));
                //.addObject("auth", Utils.getAuth());
    }
    
    @GetMapping("/pessoas/enderecos/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("/endereco/endereco_show")
                .addObject("endereco", endRepo.findById(id).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping("/pessoas/{pessoaId}/enderecos/new")
    public ModelAndView new_(@PathVariable("pessoaId") long pessoaId) {
        ModelAndView mav = newForm(pesRepo.findById(pessoaId).get());
        return mav;
    }
    
    @PostMapping(path = "/pessoas/enderecos/create")
    public ModelAndView create(@ModelAttribute Endereco endereco) {
        Endereco salvo;
        salvo = endRepo.save(endereco);
        
        ModelAndView redirect = new ModelAndView("redirect:/pessoas/enderecos/" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/pessoas/enderecos/{id}/edit", "/pessoas/enderecos/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView mav = editForm(endRepo.findById(id).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @PostMapping(path = "/pessoas/enderecos/update")
    public ModelAndView update(@ModelAttribute Endereco endereco) {
        Endereco salvo;
        salvo = endRepo.save(endereco);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/pessoas/enderecos/{id}/destroy", "/pessoas/enderecos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") long id) {
        Pessoa pessoa = endRepo.findById(id).get().getPessoa();
        
        endRepo.deleteById(id);
        ModelAndView redirect = new ModelAndView("redirect:/pessoas/" + pessoa.getId() + "/enderecos");
        return redirect;
    }
    
    private ModelAndView newForm(Pessoa pessoa) {
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        
        ModelAndView modelAndView = new ModelAndView("/endereco/endereco_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("endereco", endereco);
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }
    
    private ModelAndView editForm(Endereco endereco) {
        ModelAndView modelAndView = new ModelAndView("/endereco/endereco_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("endereco", endereco);
                //.addObject("auth", Utils.getAuth());
        return modelAndView;
    }
    
                    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
