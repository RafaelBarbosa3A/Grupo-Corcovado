/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Desconto;
import br.senac.corcovado.model.repository.DescontoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
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
 * @author Diego
 */
@Controller
public class DescontoController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private DescontoRepository descRepo;
    
    @GetMapping("/produtos/{pid}/descontos/new")
    public ModelAndView new_(@PathVariable("pid") long pid) {
        Desconto desconto = new Desconto();
        desconto.setProduto(prodRepo.findById(pid).get());
        return form("create", pid)
                .addObject("desconto", desconto);
    }

    @PostMapping(path = "/produtos/{pid}/descontos/create")
    public ModelAndView create(@PathVariable("pid") long pid, 
            @Valid @ModelAttribute Desconto desconto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("create", pid);
        }
        
        //Desconto salvo = 
        descRepo.save(desconto);
        //redirectAttributes.addFlashAttribute("mensagem", "Desconto cadastrado com sucesso!!!");
        return new ModelAndView("redirect:/produtos/" + pid);
    }

    @GetMapping({"/produtos/{pid}/descontos/{id}/edit", "/produtos/{pid}/descontos/edit/{id}" })
    public ModelAndView edit(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        return form("update", pid)
                .addObject("desconto", descRepo.findById(id).get());
    }

    @PostMapping(path = "/produtos/{pid}/descontos/update")
    public ModelAndView update(@PathVariable("pid") long pid, 
            @Valid @ModelAttribute Desconto desconto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("update", pid);
        }
        
        //Desconto salvo = 
        descRepo.save(desconto);
        //redirectAttributes.addFlashAttribute("mensagem", "Desconto alterado com sucesso!!!");
        return new ModelAndView("redirect:/produtos/" + pid);
    }

    @PostMapping(path = { "/produtos/{pid}/descontos/{id}/destroy", "/produtos/{pid}/descontos/destroy/{id}" })
    public ModelAndView destroy(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        if (descRepo.existsById(id) && descRepo.findById(id).get().getProduto().getId() == pid) {
            descRepo.deleteById(id);
            return new ModelAndView("redirect:/produtos/" + pid);
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
    
    private ModelAndView form(String action, long pid) {
        return new ModelAndView("/desconto/desconto_form")
                .addObject("produto", prodRepo.findById(pid).get())
                .addObject("action", action);
    }
    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
