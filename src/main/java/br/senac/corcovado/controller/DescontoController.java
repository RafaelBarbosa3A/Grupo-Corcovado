package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.DescontoDao;
import br.senac.corcovado.model.entity.Desconto;
import br.senac.corcovado.model.validator.DescontoValidador;
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
public class DescontoController {

    @GetMapping("/")
    public static String inserir(Desconto desconto) {
        try {
            DescontoValidador.validar(desconto);

            DescontoDao.inserir(desconto);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Desconto desconto) {
        try {
            DescontoValidador.validar(desconto);

            DescontoDao.atualizar(desconto);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Desconto desconto;
        try {
            desconto = DescontoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("desconto", desconto);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Desconto> descontos = DescontoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("descontos", descontos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        DescontoDao.excluir(id);

        return "home/index";
    }
}
