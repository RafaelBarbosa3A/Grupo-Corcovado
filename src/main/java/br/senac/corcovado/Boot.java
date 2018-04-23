package br.senac.corcovado;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Bean
    public CommandLineRunner populateDB(DepartamentoRepository deptoRepo, CategoriaRepository cateRepo, ProdutoRepository prodRepo, PrecoRepository precoRepo) {
        return (String[] args) -> {
            deptoRepo.save(new Departamento(0L, "Alimentos", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Básicos", deptoRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Matinais", deptoRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Doces", deptoRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Bebidas", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Alcoólicas", deptoRepo.findById(2L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Não Alcoólicas", deptoRepo.findById(2L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Limpeza", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Doméstica", deptoRepo.findById(3L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Roupas", deptoRepo.findById(3L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Higiene", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Pessoal", deptoRepo.findById(4L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Perfumaria", deptoRepo.findById(4L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Bebes", deptoRepo.findById(4L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Feira", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Frutas", deptoRepo.findById(5L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Verduras", deptoRepo.findById(5L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Legumes", deptoRepo.findById(5L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Ovos", deptoRepo.findById(5L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Açougue", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Carnes", deptoRepo.findById(6L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Pescados", deptoRepo.findById(6L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Aves", deptoRepo.findById(6L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            deptoRepo.save(new Departamento(0L, "Pets", new ArrayList<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Cães", deptoRepo.findById(7L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Gatos", deptoRepo.findById(7L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Aves", deptoRepo.findById(7L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));

            prodRepo.save(new Produto(0L, "Picanha", "Picanha Premium Gourmet", "Presoboi", "3265845321", "", 987, 0, cateRepo.findById(15L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            precoRepo.save(new Preco(0L, 65.99, prodRepo.findById(1L).get(), Nivel.BASIC, System.currentTimeMillis(), System.currentTimeMillis()));

        };
    }
}
