
package br.senac.corcovado;

import br.senac.corcovado.model.entity.Categoria;
// import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
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
    public CommandLineRunner populateDB(CategoriaRepository cateRepo, 
            ProdutoRepository prodRepo, 
            PrecoRepository precoRepo, 
            VendaRepository vendaRepo,
            ProdutoVendidoRepository pvRepo) {
        return (String[] args) -> {
            cateRepo.save(new Categoria(0L, "Alimentos", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Bebidas", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Carnes", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Congelados", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Doces", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Feira", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Matinais", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Snacks", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "PetShop", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Limpeza", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Bebês", System.currentTimeMillis(), System.currentTimeMillis(), true));
            cateRepo.save(new Categoria(0L, "Perfumaria", System.currentTimeMillis(), System.currentTimeMillis(), true));

            prodRepo.save(new Produto(0L, "Picanha", "Picanha Premium Gourmet", "Presoboi", "3265845321", "https://picsum.photos/300/200/?random", 987, 0, cateRepo.findById(3L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            precoRepo.save(new Preco(0L, 65.99, prodRepo.findById(1L).get(), Nivel.BASIC, System.currentTimeMillis(), System.currentTimeMillis()));
            
            vendaRepo.save(new Venda());
            
            //pvRepo.save(new ProdutoVendido(0L, prodRepo.findById(1L).get(), vendaRepo.findById(1L).get(), 3, 65.99*3, System.currentTimeMillis(), System.currentTimeMillis(), true));
        };
    }
}
