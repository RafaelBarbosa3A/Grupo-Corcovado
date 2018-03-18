package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.ReposicaoDao;
import br.senac.corcovado.model.entity.Reposicao;
import br.senac.corcovado.model.validator.ReposicaoValidador;
import br.senac.corcovado.model.validator.Validador;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wesley
 */
@Controller
public class ReposicaoController {

    @GetMapping("/")
    public static String inserir(Reposicao reposicao) {
        try {
            ReposicaoValidador.validar(reposicao);

            ReposicaoDao.inserir(reposicao);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Reposicao reposicao) {
        try {
            ReposicaoValidador.validar(reposicao);

            ReposicaoDao.atualizar(reposicao);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Reposicao reposicao;
        try {
            reposicao = ReposicaoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("reposicao", reposicao);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Reposicao> reposicao = ReposicaoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("reposicao", reposicao);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        ReposicaoDao.excluir(id);

        return "home/index";
    }
}
