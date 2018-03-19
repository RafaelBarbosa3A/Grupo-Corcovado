package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.VendaDao;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.validator.VendaValidador;
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
@RequestMapping("/vendas")
public class VendaController {

    @GetMapping("/create")
    public static ModelAndView create(Venda venda) {
        try {
            VendaValidador.validar(venda);

            VendaDao.create(venda);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/update")
    public static ModelAndView update(Venda venda) {
        try {
            VendaValidador.validar(venda);

            VendaDao.update(venda);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/search")
    public static ModelAndView search(long id) {
        try {
            return new ModelAndView("home/index", "venda", VendaDao.search(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/list")
    public static ModelAndView list() {
        try {
            return new ModelAndView("home/index", "vendas", VendaDao.list());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ModelAndView("home/index");
        }
    }

    @GetMapping("/destroy")
    public static ModelAndView destroy(long id) {
        try {
            VendaDao.destroy(id);

            return new ModelAndView("home/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("home/index");
        }
    }
}
