package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.Resposta_Dao;
import br.senac.corcovado.model.entity.Resposta;
import br.senac.corcovado.model.validator.Resposta_Validador;
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
public class Resposta_Controller {

    @GetMapping("/")
    public static String inserir(Resposta resposta) {
        try {
            Resposta_Validador.validar(resposta);

            Resposta_Dao.inserir(resposta);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Resposta resposta) {
        try {
            Resposta_Validador.validar(resposta);

            Resposta_Dao.atualizar(resposta);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Resposta resposta;
        try {
            resposta = Resposta_Dao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("resposta", resposta);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Resposta> respostas = Resposta_Dao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("respostas", respostas);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        Resposta_Dao.excluir(id);

        return "home/index";
    }
}
