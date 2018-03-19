package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.DepartamentoDao;
import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.validator.DepartamentoValidador;
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
@RequestMapping("/departamentos")
public class DepartamentoController {

    @GetMapping("/create")
    public static ModelAndView create(Departamento departamento) {
        try {
            DepartamentoValidador.validar(departamento);

            DepartamentoDao.create(departamento);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Departamento departamento) {
        try {
            DepartamentoValidador.validar(departamento);

            DepartamentoDao.update(departamento);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "departamento", DepartamentoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "departamentos", DepartamentoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            DepartamentoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }       
    }
}
