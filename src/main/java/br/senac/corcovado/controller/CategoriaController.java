package br.senac.corcovado.controller;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.service.AuthService;
//import br.senac.corcovado.model.repository.DepartamentoRepository;
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
public class CategoriaController {

    @Autowired private CategoriaRepository repository;

    // @Autowired private DepartamentoRepository deptoRepository;

    @GetMapping("/categorias")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/categoria/categoria_list");
        mav.addObject("categorias", repository.findAll());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @GetMapping("/categorias/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/categoria/categoria_show");
        mav.addObject("categoria", repository.findCategoriaById(id).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @GetMapping("/categorias/new")
    public ModelAndView new_() {
        ModelAndView mav = newForm();
        return mav;
    }

    @PostMapping(path = "/categorias/create")
    public ModelAndView create(@ModelAttribute Categoria categoria) {
        Categoria salvo;

        //TODO implementar validador via @Valid
        salvo = repository.save(categoria);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }

    @GetMapping({ "/categorias/{id}/edit", "/categorias/edit/{id}" })
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mav = editForm(repository.findCategoriaById(id).get());
                //.addObject("auth", Utils.getAuth());
        return mav;
    }

    @PostMapping(path = "/categorias/update")
    public ModelAndView update(@ModelAttribute Categoria categoria) {
        Categoria salvo;

        //TODO implementar validador via @Valid
        salvo = repository.save(categoria);

        ModelAndView redirect = new ModelAndView("redirect:" + salvo.getId());
        return redirect;
    }

    @PostMapping(path = { "/categorias/{id}/destroy", "/categorias/destroy/{id}" })
    public ModelAndView destroy(@PathVariable("id") Long id) {
        repository.deleteById(id);
        ModelAndView redirect = new ModelAndView("redirect:/categorias");
        return redirect;
    }

    private ModelAndView newForm() {
        return new ModelAndView("/categoria/categoria_form")
                .addObject("action", "create")
                .addObject("categoria", new Categoria());
                //.addObject("auth", Utils.getAuth());
    }
    
    private ModelAndView editForm(Categoria categoria) {
        return new ModelAndView("/categoria/categoria_form")
                .addObject("action", "update")
                .addObject("categoria", categoria);
                //.addObject("auth", Utils.getAuth());
    }
    
        
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
