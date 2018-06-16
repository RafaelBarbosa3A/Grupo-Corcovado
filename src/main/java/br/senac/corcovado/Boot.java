package br.senac.corcovado;

import br.senac.corcovado.model.entity.*;
import br.senac.corcovado.model.repository.CategoriaRepository;
import br.senac.corcovado.model.repository.DescontoRepository;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PapelRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.ProdutoVendidoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public CommandLineRunner populateDB(CategoriaRepository cateRepo, ProdutoRepository prodRepo, DescontoRepository descRepo,
            PapelRepository papelRepo, PessoaRepository pessRepo, EnderecoRepository endeRepo,
            VendaRepository vendRepo, ProdutoVendidoRepository pvRepo,
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
            cateRepo.save(new Categoria(0L, "Perfumaria", System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            
            GregorianCalendar now = new GregorianCalendar();
            now.add(Calendar.DAY_OF_MONTH, -1);
            GregorianCalendar future = new GregorianCalendar();
            future.add(Calendar.DAY_OF_MONTH, 20);
            
            prodRepo.save(new Produto(0L, "Picanha", "Picanha Premium Gourmet", "Do chef", "3265845321", "https://www.paodeacucar.com/img/uploads/1/396/546396.png", 987, 1, new HashSet<>(), cateRepo.findById(3L).get(), 55.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            descRepo.save(new Desconto(0L, 50D, prodRepo.findById(1L).get(), now.getTime(), future.getTime(), System.currentTimeMillis(), System.currentTimeMillis()));
            prodRepo.save(new Produto(0L, "Acém moido", "Acém moido para hamburguer", "Do chef", "3265763532", "https://www.paodeacucar.com/img/uploads/1/557/540557.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 15.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "arroz", "arroz tipo 1", "Tio João", "3265763532", "https://www.paodeacucar.com/img/uploads/1/678/510678.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 10.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "feijão preto", "ideal para feijoada", "qualitá", "3265763532", "https://www.paodeacucar.com/img/uploads/1/426/474426.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 8.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "refrigerante laranja", "o melhor", "dolly", "3265763532", "https://www.paodeacucar.com/img/uploads/1/245/535245.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 5.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "agua mineral", "natural cristal", "cristal", "3265763532", "https://www.paodeacucar.com/img/uploads/1/8/543008.JPG", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 3.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "hamburguer congelado", "instantaneo", "sadia", "3265763532", "https://www.paodeacucar.com/img/uploads/1/793/444793.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 20.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "bala de goma", "doce", "fini", "3265763532", "https://www.paodeacucar.com/img/uploads/1/278/545278.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 1.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "repolho", "salada", "natural", "3265763532", "https://www.paodeacucar.com/img/uploads/1/42/511042.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 1.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "brocolis", "salada", "natural", "3265763532", "https://www.paodeacucar.com/img/uploads/1/441/528441.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 3.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "cereal", "aquele que te da energia", "sucrilhos", "3265763532", "https://www.paodeacucar.com/img/uploads/1/962/507962.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 10.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "batata chips", "batata da onda", "ruffles", "3265763532", "https://www.paodeacucar.com/img/uploads/1/102/546102.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 8.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "ração p/ gato", "whiskas", "whiskas", "3265763532", "https://www.paodeacucar.com/img/uploads/1/642/525642.png", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 16.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "desinfetante", "limpeza eficiente", "bom bril", "3265763532", "https://www.paodeacucar.com/img/uploads/1/729/511729.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 5.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "perfume aerosol", "proteção 72 horas", "rexona", "3265763532", "https://www.paodeacucar.com/img/uploads/1/510/531510.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 9.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "sabão em pó", "multiuso", "omo", "3265763532", "https://www.paodeacucar.com/img/uploads/1/956/534956.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 12.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            prodRepo.save(new Produto(0L, "coleira animal", "antipulgas", "pet", "3265763532", "http://www.patinhaspetshop.com.br/wp-content/uploads/2017/08/coleira-abacaxi-cachorro.jpg", 54, 0, new HashSet<>(), cateRepo.findById(3L).get(), 30.0, System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            papelRepo.save(new Papel("Usuário","ROLE_USER"));
            papelRepo.save(new Papel("Administrador","ROLE_ADMIN"));
            
            pessRepo.save(new Pessoa(0L, "Cliente", "682.519.232-98", "mail@mail.com", passwordEncoder.encode("senha"), new HashSet<>(), new HashSet<>(Arrays.asList(papelRepo.findById(1L).get())), System.currentTimeMillis(), System.currentTimeMillis(), true));
            pessRepo.save(new Pessoa(0L, "Admin", "918.458.314-20", "adm@mail.com", passwordEncoder.encode("senha"), new HashSet<>(), new HashSet<>(Arrays.asList(papelRepo.findById(1L).get(), papelRepo.findById(2L).get())), System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            endeRepo.save(new Endereco(0L, "Av. Paulista", "149", "Bela Vista", "São Paulo", "SP", "01311-200", "", pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            endeRepo.save(new Endereco(0L, "Rua dos Jequitibás", "190", "Jabaquara", "São Paulo", "SP", "04309-011", "", pessRepo.findById(1L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            endeRepo.save(new Endereco(0L, "Av. Faria Lima", "7735", "Cidade Jardim", "São Paulo", "SP", "01122-333", "", pessRepo.findById(2L).get(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            
            vendRepo.save(new Venda(0L, pessRepo.findById(1L).get(), endeRepo.findById(1L).get(), Status.ENCERRADO, 10D, 50D, Pagamento.BOLETO, "comprovante", future.getTime(), "codigoRastreamento", new HashSet<>(), System.currentTimeMillis(), System.currentTimeMillis(), true));
            pvRepo.save(new ProdutoVendido(0L, prodRepo.findById(1L).get(), vendRepo.findById(1L).get(), 1, 50D, System.currentTimeMillis(), System.currentTimeMillis(), true));
        };
    }
}
