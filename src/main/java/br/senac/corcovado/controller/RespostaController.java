package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.RespostaDao;
import br.senac.corcovado.model.entity.Resposta;
import br.senac.corcovado.model.validator.RespostaValidador;
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
public class RespostaController {

    @GetMapping("/")
    public static String inserir(Resposta resposta) {
        try {
            RespostaValidador.validar(resposta);

            RespostaDao.inserir(resposta);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Resposta resposta) {
        try {
            RespostaValidador.validar(resposta);

            RespostaDao.atualizar(resposta);

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
            resposta = RespostaDao.obter(id);

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
        List<Resposta> respostas = RespostaDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("respostas", respostas);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        RespostaDao.excluir(id);

        return "home/index";
    }
}
