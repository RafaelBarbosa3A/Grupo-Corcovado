package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.AvisoDao;
import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.validator.AvisoValidador;
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
public class AvisoController {

    @GetMapping("/")
    public static String inserir(Aviso aviso) {
        try {
            AvisoValidador.validar(aviso);

            AvisoDao.inserir(aviso);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Aviso aviso) {
        try {
            AvisoValidador.validar(aviso);

            AvisoDao.atualizar(aviso);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        try {
            Aviso aviso = AvisoDao.obter(id);

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
        List<Aviso> avisos = AvisoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("avisos", avisos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        AvisoDao.excluir(id);

        return "home/index";
    }
}
