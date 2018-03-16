package br.com.corcovado.controllers;

import br.com.corcovado.dao.Dao;
import br.com.corcovado.dao.Preco_Dao;
import br.com.corcovado.model.Preco;
import br.com.corcovado.validators.Preco_Validador;
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
public class Preco_Controller {

    @GetMapping("/")
    public static String inserir(Preco preco) {
        try {
            Preco_Validador.validar(preco);

            Preco_Dao.inserir(preco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Preco preco) {
        try {
            Preco_Validador.validar(preco);

            Preco_Dao.atualizar(preco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Preco preco;
        try {
            preco = Preco_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("preco", preco);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Preco> precos = Preco_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("precos", precos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Preco_Dao.excluir(id);

        return "home/index";
    }
}
