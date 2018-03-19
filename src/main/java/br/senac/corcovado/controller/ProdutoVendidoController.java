package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.ProdutoVendidoDao;
import br.senac.corcovado.model.entity.Produto_Vendido;
import br.senac.corcovado.model.validator.ProdutoVendidoValidador;
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
@RequestMapping("/produtos_vendidos")
public class ProdutoVendidoController {

    @GetMapping("/create")
    public static ModelAndView create(Produto_Vendido pv) {
        try {
            ProdutoVendidoValidador.validar(pv);

            ProdutoVendidoDao.create(pv);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Produto_Vendido pv) {
        try {
            ProdutoVendidoValidador.validar(pv);

            ProdutoVendidoDao.update(pv);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "produto_vendido", ProdutoVendidoDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "produtos_vendidos", ProdutoVendidoDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            ProdutoVendidoDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
