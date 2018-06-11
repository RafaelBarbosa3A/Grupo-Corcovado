package br.senac.corcovado.controller;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.CategoriaRepository;
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
 * @author wesley
 */
@Controller
public class ProdutoController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private CategoriaRepository cateRepo;

    @GetMapping("/produtos")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/produto/produto_list");
        mav.addObject("produtos", prodRepo.findAll());
        return mav;
    }

    @GetMapping("/produtos/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        return new ModelAndView("/produto/produto_show")
                .addObject("produto", prodRepo.findProdutoById(id).get());
    }

    @GetMapping("/produtos/new")
    public ModelAndView new_() {
        return form("create")
                .addObject("produto", new Produto());
    }

    @PostMapping(path = "/produtos/create")
    public ModelAndView create(@Valid @ModelAttribute Produto produto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("create");
        }
        
        Produto salvo = prodRepo.save(produto);
        //redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!!!");
        return new ModelAndView("redirect:" + salvo.getId());
    }

    @GetMapping({"/produtos/{id}/edit", "/produtos/edit/{id}" })
    public ModelAndView edit(@PathVariable("id") long id) {
        return form("update")
                .addObject("produto", prodRepo.findProdutoById(id).get());
    }

    @PostMapping(path = "/produtos/update")
    public ModelAndView update(@Valid @ModelAttribute Produto produto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("update");
        }
        
        Produto salvo = prodRepo.save(produto);
        //redirectAttributes.addFlashAttribute("mensagem", "Produto alterado com sucesso!!!");
        return new ModelAndView("redirect:" + salvo.getId());
    }

    @PostMapping(path = { "/produtos/{id}/destroy", "/produtos/destroy/{id}" })
    public ModelAndView destroy(@PathVariable("id") long id) {
        prodRepo.deleteById(id);
        return new ModelAndView("redirect:/produtos");
    }

    private ModelAndView form(String action) {
        return new ModelAndView("/produto/produto_form")
                .addObject("categorias", cateRepo.findAll())
                .addObject("action", action);
    }
    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
