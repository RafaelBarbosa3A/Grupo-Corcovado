package br.com.corcovado.controllers;

import br.com.corcovado.dao.Venda_Dao;
import br.com.corcovado.model.Venda;
import br.com.corcovado.validators.Validador;
import br.com.corcovado.validators.Venda_Validador;
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
public class Venda_Controller {

    @GetMapping("/")
    public static String inserir(Venda venda) {
        try {
            Venda_Validador.validar(venda);

            Venda_Dao.inserir(venda);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Venda venda) {
        try {
            Venda_Validador.validar(venda);

            Venda_Dao.atualizar(venda);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        try {
            Venda venda = Venda_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("venda", venda);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Venda> vendas = Venda_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("vendas", vendas);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Venda_Dao.excluir(id);

        return "home/index";
    }
}
