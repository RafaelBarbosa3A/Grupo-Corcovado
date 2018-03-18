package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.Produto_Dao;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.validator.Produto_Validador;
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
public class Produto_Controller {

    @GetMapping("/")
    public static String inserir(Produto produto) {
        try {
            Produto_Validador.validar(produto);

            Produto_Dao.inserir(produto);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Produto produto) {
        try {
            Produto_Validador.validar(produto);

            Produto_Dao.atualizar(produto);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Produto produto;
        try {
            produto = Produto_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("produto", produto);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Produto> produtos = Produto_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Produto_Dao.excluir(id);

        return "home/index";
    }
}
