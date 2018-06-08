package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
//import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.CategoriaRepository;
//import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.service.AuthService;
import javax.validation.Valid;
//import java.util.Iterator;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @GetMapping("/produtos/{id}")
    public ModelAndView show(@PathVariable("id") long id) {
        Produto produto = prodRepo.findProdutoById(id).get();
        return new ModelAndView("/produto/produto_show")
                .addObject("produto", produto);
                //.addObject("auth", Utils.getAuth());
    }

    @GetMapping("/produtos/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }

    @PostMapping(path = "/produtos/create")
    public ModelAndView create(@Valid @ModelAttribute Produto produto,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        Produto salvo;

        //TODO implementar validador via @Valid
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/produto/produto_form")
                .addObject("action", "create");
        }
        
        salvo = prodRepo.save(produto);

        redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!!!");
        return new ModelAndView("redirect:" + salvo.getId());
    }

    @GetMapping({ "/produtos/{id}/edit", "/produtos/edit/{id}" })
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView mav = editForm(prodRepo.findProdutoById(id).get());
        return mav;
    }

    @PostMapping(path = "/produtos/update")
    public ModelAndView update(@ModelAttribute Produto produto) {
        Produto salvo;

        //TODO implementar validador via @Valid
        salvo = prodRepo.save(produto);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }

    @PostMapping(path = { "/produtos/{id}/destroy", "/produtos/destroy/{id}" })
    public ModelAndView destroy(@PathVariable("id") long id) {
        prodRepo.deleteById(id);
        ModelAndView redirect = new ModelAndView("redirect:/produtos");
        return redirect;
    }

    private ModelAndView newForm() {
        return new ModelAndView("/produto/produto_form")
                .addObject("action", "create")
                .addObject("produto", new Produto())
                .addObject("categorias", cateRepo.findAll());
                //.addObject("auth", Utils.getAuth());
    }

    private ModelAndView editForm(Produto produto) {
        return new ModelAndView("/produto/produto_form")
                .addObject("action", "create")
                .addObject("produto", produto)
                .addObject("categorias", cateRepo.findAll());
                //.addObject("auth", Utils.getAuth());
    }
    
            
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
