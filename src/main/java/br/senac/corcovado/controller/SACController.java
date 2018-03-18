package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.SACDao;
import br.senac.corcovado.model.entity.SAC;
import br.senac.corcovado.model.validator.SACValidador;
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
public class SACController {

    @GetMapping("/")
    public static String inserir(SAC sac) {
        try {
            SACValidador.validar(sac);

            SACDao.inserir(sac);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(SAC sac) {
        try {
            SACValidador.validar(sac);

            SACDao.atualizar(sac);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        try {
            SAC sac = SACDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("sac", sac);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");
            return mv;
        }

    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<SAC> sacs = SACDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("sacs", sacs);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        SACDao.excluir(id);

        return "home/index";
    }
}
