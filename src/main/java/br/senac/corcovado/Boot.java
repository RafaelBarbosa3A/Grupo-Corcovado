
package br.senac.corcovado;

import br.senac.corcovado.model.entity.Cargo;
import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.entity.Endereco;
// import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.entity.Nivel;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
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
            ProdutoVendidoRepository pvRepo,
            PessoaRepository pessRepo,
            EnderecoRepository endeRepo) {
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
            
            pessRepo.save(new Pessoa(0L, "Cliente", "documento", "mail@mail.com", "senha", Nivel.BASIC, null, System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            
            // Av. Paulista, 149 - Bela Vista, São Paulo - SP, 01311-200
            endeRepo.save(new Endereco(0L, "Av. Paulista", "149", "Bela Vista", "São Paulo", "SP", "01311-200", "", false, pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis()));
            // Rua dos Jequitibás - Jabaquara, São Paulo - SP, 04309-011
            endeRepo.save(new Endereco(0L, "Rua dos Jequitibás", "190", "Jabaquara", "São Paulo", "SP", "04309-011", "", false, pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis()));
        };
    }
}
