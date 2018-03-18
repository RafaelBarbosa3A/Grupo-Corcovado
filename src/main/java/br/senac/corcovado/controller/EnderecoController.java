package br.senac.corcovado.controller;

import br.senac.corcovado.model.dao.Dao;
import br.senac.corcovado.model.dao.EnderecoDao;
import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.validator.EnderecoValidador;
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
public class EnderecoController {

    @GetMapping("/")
    public static String inserir(Endereco endereco) {
        try {
            EnderecoValidador.validar(endereco);

            EnderecoDao.inserir(endereco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static String atualizar(Endereco endereco) {
        try {
            EnderecoValidador.validar(endereco);

            EnderecoDao.atualizar(endereco);

            return "home/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "home/index";
        }
    }

    @GetMapping("/")
    public static ModelAndView obter(long id) {
        Endereco endereco;
        try {
            endereco = EnderecoDao.obter(id);

            ModelAndView mv = new ModelAndView("home/index");
            mv.addObject("endereco", endereco);
            return mv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ModelAndView mv = new ModelAndView("home/index");            
            return mv;
        }
    }

    @GetMapping("/")
    public static ModelAndView listar() {
        List<Endereco> enderecos = EnderecoDao.listar();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("enderecos", enderecos);
        return mv;
    }

    @GetMapping("/")
    public static String excluir(long id) {
        EnderecoDao.excluir(id);

        return "home/index";
    }
}
