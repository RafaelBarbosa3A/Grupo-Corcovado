package br.com.corcovado.controllers;

import br.com.corcovado.dao.Dao;
import br.com.corcovado.dao.SAC_Dao;
import br.com.corcovado.model.SAC;
import br.com.corcovado.validators.SAC_Validador;
import br.com.corcovado.validators.Validador;
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
public class SAC_Controller {

    @GetMapping("/")
    public static String inserir(SAC sac) {
        try {
            SAC_Validador.validar(sac);

            SAC_Dao.inserir(sac);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(SAC sac) {
        try {
            SAC_Validador.validar(sac);

            SAC_Dao.atualizar(sac);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        try {
            SAC sac = SAC_Dao.obter(id);

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
        List<SAC> sacs = SAC_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("sacs", sacs);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        SAC_Dao.excluir(id);

        return "home/index";
    }
}
