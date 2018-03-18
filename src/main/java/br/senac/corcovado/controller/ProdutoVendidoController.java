package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.ProdutoVendidoDao;
import br.senac.corcovado.model.entity.Produto_Vendido;
import br.senac.corcovado.model.validator.ProdutoVendidoValidador;
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
public class ProdutoVendidoController {

    @GetMapping("/")
    public static String inserir(Produto_Vendido pv) {
        try {
            ProdutoVendidoValidador.validar(pv);

            ProdutoVendidoDao.inserir(pv);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Produto_Vendido pv) {
        try {
            ProdutoVendidoValidador.validar(pv);

            ProdutoVendidoDao.atualizar(pv);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Produto_Vendido pv;
        try {
            pv = ProdutoVendidoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("pv", pv);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Produto_Vendido> pvs = ProdutoVendidoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("pvs", pvs);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        ProdutoVendidoDao.excluir(id);

        return "home/index";
    }
}
