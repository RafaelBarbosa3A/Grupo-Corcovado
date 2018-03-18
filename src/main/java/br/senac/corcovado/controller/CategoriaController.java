package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.CategoriaDao;
import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.validator.CategoriaValidador;
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
public class CategoriaController {

    @GetMapping("/")
    public static String inserir(Categoria categoria) {
        try {
            CategoriaValidador.validar(categoria);

            CategoriaDao.inserir(categoria);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Categoria categoria) {
        try {
            CategoriaValidador.validar(categoria);

            CategoriaDao.atualizar(categoria);

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
            categoria = CategoriaDao.obter(id);

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
        List<Categoria> categorias = CategoriaDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("categorias", categorias);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        CategoriaDao.excluir(id);

        return "home/index";
    }
}
