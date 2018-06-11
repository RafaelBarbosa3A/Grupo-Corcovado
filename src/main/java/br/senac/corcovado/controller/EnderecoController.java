package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Desconto;
import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.service.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author wesley
 */
@Controller
public class EnderecoController {
    @Autowired private EnderecoRepository endRepo;
    @Autowired private PessoaRepository pesRepo;
    
    /*
    @GetMapping("/pessoas/{pessoaId}/enderecos")
    public ModelAndView list(@PathVariable("pessoaId") long pessoaId) {
        return new ModelAndView("/endereco/endereco_list")
                .addObject("pessoa", pesRepo.findById(pessoaId).get())
                .addObject("enderecos", endRepo.findAllByPessoaId(pessoaId));
                //.addObject("auth", Utils.getAuth());
    }
    */
    
    @GetMapping("/pessoas/{pid}/enderecos/{id}")
    public ModelAndView show(@PathVariable("pid") long pid, 
            @PathVariable("id") long id) {
        if (endRepo.findById(id).get().getPessoa().getId() == pid) {
            ModelAndView mav = new ModelAndView("/endereco/endereco_show")
                    .addObject("endereco", endRepo.findById(id).get());
            return mav;
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    @GetMapping("/pessoas/{pid}/enderecos/new")
    public ModelAndView new_(@PathVariable("pid") long pid) {
        Endereco endereco = new Endereco();
        endereco.setPessoa(pesRepo.findById(pid).get());
        
        return form("create", pid).addObject("endereco", endereco);
    }
    
    @PostMapping(path = "/pessoas/{pid}/enderecos/create")
    public ModelAndView create(@PathVariable("pid") long pid, 
            @Valid @ModelAttribute Endereco endereco, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return form("create", pid);
        }
        
        endRepo.save(endereco);
        //redirectAttributes.addFlashAttribute("mensagem", "Endereço cadastrado com sucesso!!!");
        return new ModelAndView("redirect:/pessoas/" + pid);
    }
    
    @GetMapping({"/pessoas/{pid}/enderecos/{id}/edit", "/pessoas/{pid}/enderecos/edit/{id}"})
    public ModelAndView edit(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        return form("update", pid)
                .addObject("endereco", endRepo.findById(id).get());
    }
    
    @PostMapping(path = "/pessoas/{pid}/enderecos/update")
    public ModelAndView update(@PathVariable("pid") long pid, 
            @Valid @ModelAttribute Endereco endereco, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("update", pid);
        }
        
        endRepo.save(endereco);
        //redirectAttributes.addFlashAttribute("mensagem", "Endereço alterado com sucesso!!!");
        return new ModelAndView("redirect:/pessoas/" + pid);
    }
    
    @PostMapping(path = {"/pessoas/{pid}/enderecos/{id}/destroy", "/pessoas/{pid}/enderecos/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        if (endRepo.existsById(id) && endRepo.findById(id).get().getPessoa().getId() == pid) {
            endRepo.deleteById(id);
            return new ModelAndView("redirect:/pessoas/" + pid);
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    private ModelAndView form(String action, long pid) {
        return new ModelAndView("/endereco/endereco_form")
            .addObject("pessoa", pesRepo.findById(pid).get())
            .addObject("action", action);
    }
                    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
