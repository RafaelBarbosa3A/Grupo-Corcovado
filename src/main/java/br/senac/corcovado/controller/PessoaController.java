package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.PessoaDao;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.validator.PessoaValidador;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wesley
 */
@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @GetMapping("/new")
    public static ModelAndView new_() {
        ModelAndView m =  new ModelAndView("pessoa_form");
        m.addObject("pessoa", new Pessoa());
        return m;
    }

    @GetMapping("/create")
    public static ModelAndView create(Pessoa pessoa) {
        try {
            PessoaValidador.validar(pessoa);

            PessoaDao.create(pessoa);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Pessoa pessoa) {
        try {
            PessoaValidador.validar(pessoa);

            PessoaDao.update(pessoa);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "preco", PessoaDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "precos", PessoaDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            PessoaDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
