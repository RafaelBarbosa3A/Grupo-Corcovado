package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.exception.VendaException;
import br.senac.corcovado.model.repository.DescontoRepository;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import br.senac.corcovado.model.validator.VendaValidador;
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
public class VendaController {    
    @Autowired 
    private VendaRepository repository;
    private PessoaRepository pessoaRepository;
    private EnderecoRepository enderecoRepository;
    private DescontoRepository descontoRepository;
    
    @GetMapping("/vendas")
    public ModelAndView list() {        
        ModelAndView mav = new ModelAndView("venda_list");
        mav.addObject("vendas", repository.findAll());
        return mav;
    }
    
    @GetMapping("/vendas/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {        
        ModelAndView mav = new ModelAndView("venda_show");
        mav.addObject("venda", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/vendas/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/vendas/create")
    public ModelAndView create(@ModelAttribute Venda venda) {
        Venda salvo;
        try {
            VendaValidador.validar(venda);
            salvo = repository.save(venda);
        } catch (VendaException ex) { 
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("venda", venda);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    
    @GetMapping({"/vendas/{id}/edit", "/vendas/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/vendas/update")
    public ModelAndView update(@ModelAttribute Venda venda) {
        Venda salvo;
        try {
            VendaValidador.validar(venda);
            salvo = repository.save(venda);
        } catch (VendaException ex) { 
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(venda);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/vendas/{id}/destroy", "/vendas/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/vendas");
        return redirect;
    }    
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("vendas_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());        
        modelAndView.addObject("enderecos", enderecoRepository.findAll());
        modelAndView.addObject("descontos", descontoRepository.findAll());
        modelAndView.addObject("status", Status.values());
        return modelAndView;
    }
    
    private ModelAndView editForm(Venda venda) {
        ModelAndView modelAndView = new ModelAndView("venda_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("venda", venda);
        modelAndView.addObject("pessoas", pessoaRepository.findAll());        
        modelAndView.addObject("enderecos", enderecoRepository.findAll());
        modelAndView.addObject("descontos", descontoRepository.findAll());
        modelAndView.addObject("status", Status.values());        
        return modelAndView;
    }
}
