package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.DepartamentoDao;
import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.validator.DepartamentoValidador;
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
public class DepartamentoController {

    @GetMapping("/")
    public static String inserir(Departamento departamento) {
        try {
            DepartamentoValidador.validar(departamento);

            DepartamentoDao.inserir(departamento);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Departamento departamento) {
        try {
            DepartamentoValidador.validar(departamento);

            DepartamentoDao.atualizar(departamento);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Departamento departamento;
        try {
            departamento = DepartamentoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("departamento", departamento);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Departamento> departamentos = DepartamentoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("departamentos", departamentos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        DepartamentoDao.excluir(id);

        return "home/index";
    }
}
