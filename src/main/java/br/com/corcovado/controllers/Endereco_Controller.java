package br.com.corcovado.controllers;

import br.com.corcovado.dao.Dao;
import br.com.corcovado.dao.Endereco_Dao;
import br.com.corcovado.model.Endereco;
import br.com.corcovado.validators.Endereco_Validador;
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
public class Endereco_Controller {

    @GetMapping("/")
    public static String inserir(Endereco endereco) {
        try {
            Endereco_Validador.validar(endereco);

            Endereco_Dao.inserir(endereco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Endereco endereco) {
        try {
            Endereco_Validador.validar(endereco);

            Endereco_Dao.atualizar(endereco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Endereco endereco;
        try {
            endereco = Endereco_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("endereco", endereco);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Endereco> enderecos = Endereco_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("enderecos", enderecos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Endereco_Dao.excluir(id);

        return "home/index";
    }
}
