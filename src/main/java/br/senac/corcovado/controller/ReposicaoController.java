package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.ReposicaoDao;
import br.senac.corcovado.model.entity.Reposicao;
import br.senac.corcovado.model.validator.ReposicaoValidador;
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
@RequestMapping("/reposicoes")
public class ReposicaoController {

    @GetMapping("/create")
    public static ModelAndView create(Reposicao reposicao) {
        try {
            ReposicaoValidador.validar(reposicao);

            ReposicaoDao.create(reposicao);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Reposicao reposicao) {
        try {
            ReposicaoValidador.validar(reposicao);

            ReposicaoDao.update(reposicao);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "reposicao", ReposicaoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "reposicoes", ReposicaoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            ReposicaoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
