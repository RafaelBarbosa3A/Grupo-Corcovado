package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.Reposicao_Dao;
import br.senac.corcovado.model.entity.Reposicao;
import br.senac.corcovado.model.validator.Reposicao_Validador;
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
public class Reposicao_Controller {

    @GetMapping("/")
    public static String inserir(Reposicao reposicao) {
        try {
            Reposicao_Validador.validar(reposicao);

            Reposicao_Dao.inserir(reposicao);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Reposicao reposicao) {
        try {
            Reposicao_Validador.validar(reposicao);

            Reposicao_Dao.atualizar(reposicao);

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
            reposicao = Reposicao_Dao.obter(id);

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
        List<Reposicao> reposicao = Reposicao_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("reposicao", reposicao);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Reposicao_Dao.excluir(id);

        return "home/index";
    }
}
