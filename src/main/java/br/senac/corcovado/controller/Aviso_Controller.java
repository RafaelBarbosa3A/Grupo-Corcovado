package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Aviso_Dao;
import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.validator.Aviso_Validador;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wesley
 */
@Controller
public class Aviso_Controller {

    @GetMapping("/")
    public static String inserir(Aviso aviso) {
        try {
            Aviso_Validador.validar(aviso);

            Aviso_Dao.inserir(aviso);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Aviso aviso) {
        try {
            Aviso_Validador.validar(aviso);

            Aviso_Dao.atualizar(aviso);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        try {
            Aviso aviso = Aviso_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("aviso", aviso);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Aviso> avisos = Aviso_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("avisos", avisos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Aviso_Dao.excluir(id);

        return "home/index";
    }
}
