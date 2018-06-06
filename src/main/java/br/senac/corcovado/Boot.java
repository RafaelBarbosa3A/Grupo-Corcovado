package br.senac.corcovado;

import br.senac.corcovado.model.entity.*;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PapelRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Bean
    public CommandLineRunner populateDB(CategoriaRepository cateRepo, ProdutoRepository prodRepo,
            PapelRepository papelRepo, PessoaRepository pessRepo, EnderecoRepository endeRepo,
            PasswordEncoder passwordEncoder) {
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
            
            prodRepo.save(new Produto(0L, "Picanha", "Picanha Premium Gourmet", "Presoboi", "3265845321", "https://picsum.photos/300/200/?random", 987, 0, new HashSet<>(), cateRepo.findById(3L).get(), 55.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "Acém moido", "Acém moido para hamburguer", "Presoboi", "3265763532", "https://picsum.photos/300/200/?random", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 15.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            papelRepo.save(new Papel("Usuário","ROLE_USER"));
            papelRepo.save(new Papel("Administrador","ROLE_ADMIN"));
            
            pessRepo.save(new Pessoa(0L, "Cliente", "12345678", "mail@mail.com", passwordEncoder.encode("senha"), Nivel.BASIC, new HashSet<>(), new HashSet<>(Arrays.asList(papelRepo.findById(1L).get())), System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            endeRepo.save(new Endereco(0L, "Av. Paulista", "149", "Bela Vista", "São Paulo", "SP", "01311-200", "", pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            endeRepo.save(new Endereco(0L, "Rua dos Jequitibás", "190", "Jabaquara", "São Paulo", "SP", "04309-011", "", pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            pessRepo.save(new Pessoa(0L, "Admin", "87654321", "adm@mail.com", passwordEncoder.encode("senha"), Nivel.BASIC, new HashSet<>(), new HashSet<>(Arrays.asList(papelRepo.findById(1L).get(), papelRepo.findById(2L).get())), System.currentTimeMillis(), System.currentTimeMillis(), true));
        };
    }
}
