package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/vendas")
    public ModelAndView list() {        
        ModelAndView mav = new ModelAndView("/venda/venda_list");
        mav.addObject("vendas", repository.findAll())
                .addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping("/vendas/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {        
        ModelAndView mav = new ModelAndView("/venda/venda_show");
        mav.addObject("venda", repository.findById(Long.parseLong(usId)).get())
                .addObject("auth", Utils.getAuth());
        return mav;
    }
}
