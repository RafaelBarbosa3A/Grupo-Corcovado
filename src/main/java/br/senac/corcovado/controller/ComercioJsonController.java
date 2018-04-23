package br.senac.corcovado.controller;
import br.senac.corcovado.Utils;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ComercioJsonController {
    @Autowired private ProdutoRepository prodRepo;
    @Autowired private PrecoRepository precoRepo;


    @GetMapping("/comercio_json")
    public List<Produto> list() {
        List<Produto> lista = Utils.asList(prodRepo.findAll());
        for(Produto p: lista) {
            p.setPrecos(Utils.asList(precoRepo.findAllByProdutoId(p.getId())));
        }

        return lista;
    }
    
    

}
