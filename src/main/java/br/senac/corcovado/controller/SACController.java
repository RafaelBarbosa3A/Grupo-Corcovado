package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Resposta;
import br.senac.corcovado.model.entity.Sac;
import br.senac.corcovado.model.entity.StatusMensagem;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.RespostaRepository;
import br.senac.corcovado.model.repository.SacRepository;
import br.senac.corcovado.model.service.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SACController {
    
    @Autowired private SacRepository sacRepo;
    @Autowired private RespostaRepository respRepo;
    @Autowired private PessoaRepository pessRepo;

    @GetMapping("/atendimentos")
    public ModelAndView list() {
        Sac sac = new Sac();
        sac.setPessoa((Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        if (authServ.getCurrentUser().getAuthorities().contains("ROLE_ADMIN")) {
            return new ModelAndView("/sac/sac_list")
                    .addObject("sacs", sacRepo.findAllByStatusMensagemNotOrderByStatusMensagemDesc(StatusMensagem.ENCERRADO))
                    .addObject("sac", sac); 
        } else {
            return new ModelAndView("/sac/sac_list")
                        .addObject("sacs", sacRepo.findAllByPessoaIdAndStatusMensagemNotOrderByStatusMensagemDesc(sac.getPessoa().getId(), StatusMensagem.ENCERRADO))
                        .addObject("sac", sac); 
        }
    }
    
    @PostMapping(path = "/atendimentos/create")
    public ModelAndView create(@Valid @ModelAttribute Sac sac, 
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        //sac.setPessoa((Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/sac/sac_list")
                .addObject("sacs", sacRepo.findAll());
        }
        Sac salvo = sacRepo.save(sac);
        
        ModelAndView redirect = new ModelAndView("redirect:/atendimentos/" + salvo.getId());
        return redirect;
    }
    
    
    @GetMapping("/atendimentos/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        Resposta resposta = new Resposta();
        resposta.setPessoa((Pessoa) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        resposta.setSac(sacRepo.findById(id).get());
        
        return new ModelAndView("/sac/sac_show")
                .addObject("sac", sacRepo.findById(id).get())
                .addObject("stati", StatusMensagem.values())
                .addObject("newResposta", resposta);
    }
    
    @PostMapping(path = "/atendimentos/{id}/respostas/create")
    public ModelAndView createResposta(@PathVariable("id") long id, 
            @Valid @ModelAttribute Resposta resposta, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/sac/sac_show")
                    .addObject("stati", StatusMensagem.values())
                    .addObject("sac", sacRepo.findById(id).get());
        }
        Resposta salvo = respRepo.save(resposta);
        ModelAndView redirect = new ModelAndView("redirect:/atendimentos/" + id);
        return redirect;
    }
    
    @PostMapping(path = "/atendimentos/update")
    public ModelAndView update(@ModelAttribute Sac sac) {
        Sac oldSac = sacRepo.findById(sac.getId()).get();
        oldSac.setStatusMensagem(sac.getStatusMensagem());
        sacRepo.save(oldSac);
        
        ModelAndView redirect = new ModelAndView("redirect:/atendimentos");
        return redirect;
    }
    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
