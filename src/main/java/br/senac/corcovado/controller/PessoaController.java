package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.PessoaDao;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.validator.PessoaValidador;
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
public class PessoaController {

    @GetMapping("/")
    public static String inserir(Pessoa pessoa) {
        try {
            PessoaValidador.validar(pessoa);

            PessoaDao.inserir(pessoa);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Pessoa pessoa) {
        try {
            PessoaValidador.validar(pessoa);

            PessoaDao.atualizar(pessoa);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Pessoa pessoa;
        try {
            pessoa = PessoaDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("pessoa", pessoa);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Pessoa> pessoas = PessoaDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("pessoas", pessoas);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id
    ) {
        PessoaDao.excluir(id);

        return "home/index";
    }
}
