package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.AvisoDao;
import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.validator.AvisoValidador;
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
@RequestMapping("/avisos")
public class AvisoController {
    
    @GetMapping("/create")
    public static ModelAndView create(Aviso aviso) {
        try {
            AvisoValidador.validar(aviso);

            AvisoDao.create(aviso);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
    
    @GetMapping("/update")
    public static ModelAndView update(Aviso aviso) {
        try {
            AvisoValidador.validar(aviso);

            AvisoDao.uptade(aviso);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
    
    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "aviso", AvisoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
    
    @GetMapping("/list")
    public static ModelAndView list() {        
        try {
            return new ModelAndView("home/index", "avisos", AvisoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            AvisoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
