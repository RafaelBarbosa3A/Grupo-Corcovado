package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.PrecoDao;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.validator.PrecoValidador;
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
public class PrecoController {

    @GetMapping("/")
    public static String inserir(Preco preco) {
        try {
            PrecoValidador.validar(preco);

            PrecoDao.inserir(preco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Preco preco) {
        try {
            PrecoValidador.validar(preco);

            PrecoDao.atualizar(preco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Preco preco;
        try {
            preco = PrecoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("preco", preco);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Preco> precos = PrecoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("precos", precos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        PrecoDao.excluir(id);

        return "home/index";
    }
}
