package br.com.corcovado.controllers;

import br.com.corcovado.dao.Categoria_Dao;
import br.com.corcovado.dao.Dao;
import br.com.corcovado.model.Categoria;
import br.com.corcovado.validators.Categoria_Validador;
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
public class Categoria_Controller {

    @GetMapping("/")
    public static String inserir(Categoria categoria) {
        try {
            Categoria_Validador.validar(categoria);

            Categoria_Dao.inserir(categoria);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Categoria categoria) {
        try {
            Categoria_Validador.validar(categoria);

            Categoria_Dao.atualizar(categoria);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Categoria categoria;
        try {
            categoria = Categoria_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("categoria", categoria);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Categoria> categorias = Categoria_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("categorias", categorias);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Categoria_Dao.excluir(id);

        return "home/index";
    }
}
