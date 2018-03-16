package br.com.corcovado.controllers;

import br.com.corcovado.dao.Dao;
import br.com.corcovado.dao.Produto_Reposicao_Dao;
import br.com.corcovado.model.Produto_Reposicao;
import br.com.corcovado.validators.Produto_Reposicao_Validador;
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
public class Produto_Reposicao_Controller {

    @GetMapping("/")
    public static String inserir(Produto_Reposicao pr) {
        try {
            Produto_Reposicao_Validador.validar(pr);

            Produto_Reposicao_Dao.inserir(pr);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Produto_Reposicao pr) {
        try {
            Produto_Reposicao_Validador.validar(pr);

            Produto_Reposicao_Dao.atualizar(pr);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Produto_Reposicao pr;
        try {
            pr = Produto_Reposicao_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("pr", pr);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Produto_Reposicao> prs = Produto_Reposicao_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("prs", prs);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Dao.excluir(id);

        return "home/index";
    }
}
