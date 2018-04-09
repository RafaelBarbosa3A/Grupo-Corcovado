package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.ProdutoReposicao;
import br.senac.corcovado.model.exception.ProdutoReposicaoException;
import br.senac.corcovado.model.repository.ProdutoReposicaoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ReposicaoRepository;
import br.senac.corcovado.model.validator.ProdutoReposicaoValidador;
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
public class ProdutoReposicaoController {

    @Autowired 
    private ProdutoReposicaoRepository repository;
    private ProdutoRepository produtoRepository;
    private ReposicaoRepository reposicaoRepository;
    
    @GetMapping("/produtos_reposicao")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("produto_reposicao_list");
        mav.addObject("produtos_reposicao", repository.findAll());
        return mav;
    }
    
    @GetMapping("/produtos_reposicao/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("produto_reposicao_show");
        mav.addObject("produto_reposicao", repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @GetMapping("/produtos_reposicao/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }
    
    @PostMapping(path = "/produtos_reposicao/create")
    public ModelAndView create(@ModelAttribute ProdutoReposicao produto_reposicao) {
        ProdutoReposicao salvo;
        try {
            ProdutoReposicaoValidador.validar(produto_reposicao);
            salvo = repository.save(produto_reposicao);
        } catch (ProdutoReposicaoException ex) { 
            Logger.getLogger(ProdutoReposicaoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = newForm();
            forward.addObject("produto_reposicao", produto_reposicao);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @GetMapping({"/produtos_reposicao/{id}/edit", "/produtos_reposicao/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") String usId) {
        ModelAndView mav = editForm(repository.findById(Long.parseLong(usId)).get());
        return mav;
    }
    
    @PostMapping(path = "/produtos_reposicao/update")
    public ModelAndView update(@ModelAttribute ProdutoReposicao produto_reposicao) {
        ProdutoReposicao salvo;
        try {
            ProdutoReposicaoValidador.validar(produto_reposicao);
            salvo = repository.save(produto_reposicao);
        } catch (ProdutoReposicaoException ex) { 
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            ModelAndView forward = editForm(produto_reposicao);
            forward.addObject("erros", ex.getErrors());
            return forward;
        }

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }
    
    @PostMapping(path = {"/produtos_reposicao/{id}/destroy", "/produtos_reposicao/destroy/{id}"})
    public ModelAndView destroy(@PathVariable("id") String usId) {
        repository.deleteById(Long.parseLong(usId));
        ModelAndView redirect = new ModelAndView("redirect:/produtos_reposicao");
        return redirect;
    }
    
    private ModelAndView newForm() {
        ModelAndView modelAndView = new ModelAndView("produto_reposicao_form");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("produto_reposicao", new ProdutoReposicao());
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("reposicoes", reposicaoRepository.findAll());
        return modelAndView;
    }
    
    private ModelAndView editForm(ProdutoReposicao produto_reposicao) {
        ModelAndView modelAndView = new ModelAndView("produto_reposicao_form");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("produto_reposicao", produto_reposicao);
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("reposicoes", reposicaoRepository.findAll());        
        return modelAndView;
    }
}
