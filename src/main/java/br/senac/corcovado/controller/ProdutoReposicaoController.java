package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.ProdutoReposicaoDao;
import br.senac.corcovado.model.entity.Produto_Reposicao;
import br.senac.corcovado.model.validator.ProdutoReposicaoValidador;
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
@RequestMapping("/produtos_reposicao")
public class ProdutoReposicaoController {

    @GetMapping("/create")
    public static ModelAndView create(Produto_Reposicao pr) {
        try {
            ProdutoReposicaoValidador.validar(pr);

            ProdutoReposicaoDao.create(pr);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/uptade")
    public static ModelAndView update(Produto_Reposicao pr) {
        try {
            ProdutoReposicaoValidador.validar(pr);

            ProdutoReposicaoDao.update(pr);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "prodReposicao", ProdutoReposicaoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "prodsReposicao", ProdutoReposicaoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            ProdutoReposicaoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
