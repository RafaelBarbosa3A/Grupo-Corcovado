package br.senac.corcovado;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.DepartamentoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.PrecoRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import java.util.GregorianCalendar;
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
	public CommandLineRunner populateDB(ProdutoRepository prodRepo, PrecoRepository precRepo,
            DepartamentoRepository depRepo, CategoriaRepository catRepo,
            PessoaRepository pessRepo, VendaRepository vendRepo) {
		return (String[] args) -> {
            depRepo.save(new Departamento(0L, "Bebidas", "Departamento de Bebidas", new GregorianCalendar(),new GregorianCalendar(), true));
            catRepo.save(new Categoria(0L, "Refrigerantes", "Departamento de Bebidas gaseificadas", 1L, new GregorianCalendar(),new GregorianCalendar(), true));
            prodRepo.save(new Produto(0L, "Coca-Cola", "Uma garrafa de Coca-cola 2L", "Spal", "65465435243", 5463, 0, 0L, new GregorianCalendar(), new GregorianCalendar(), true));
            precRepo.save(new Preco(0L, 1L, 6.99, 1L, new GregorianCalendar(), new GregorianCalendar(), true));
            
            pessRepo.save(new Pessoa(0L, "Fulano da Silva", "123456789", "fulano@bol.com.br", "senha", 1L, null, new GregorianCalendar(), new GregorianCalendar(), true));
            
            vendRepo.save(new Venda(0L, 1L, 1L, null, 1, 0D, "", "", "", "", new GregorianCalendar(), new GregorianCalendar(), true));
            
        };
    };
}