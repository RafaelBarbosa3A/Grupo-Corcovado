package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.ProdutoDao;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.validator.ProdutoValidador;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @GetMapping("/create")
    public static ModelAndView create(Produto produto) {
        try {
            ProdutoValidador.validar(produto);

            ProdutoDao.create(produto);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Produto produto) {
        try {
            ProdutoValidador.validar(produto);

            ProdutoDao.update(produto);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "produto", ProdutoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
    
    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "produtos", ProdutoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            ProdutoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
