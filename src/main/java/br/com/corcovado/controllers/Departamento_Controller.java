package br.com.corcovado.controllers;

import br.com.corcovado.dao.Dao;
import br.com.corcovado.dao.Departamento_Dao;
import br.com.corcovado.model.Departamento;
import br.com.corcovado.validators.Departamento_Validador;
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
public class Departamento_Controller {

    @GetMapping("/")
    public static String inserir(Departamento departamento) {
        try {
            Departamento_Validador.validar(departamento);

            Departamento_Dao.inserir(departamento);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Departamento departamento) {
        try {
            Departamento_Validador.validar(departamento);

            Departamento_Dao.atualizar(departamento);

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
            departamento = Departamento_Dao.obter(id);

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
        List<Departamento> departamentos = Departamento_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("departamentos", departamentos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Departamento_Dao.excluir(id);

        return "home/index";
    }
}
