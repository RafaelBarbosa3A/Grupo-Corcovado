package br.senac.corcovado.controller;

import br.senac.corcovado.model.entity.Endereco;
import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class LoginController {
    
    @Autowired PessoaRepository pessRepo;
    @Autowired EnderecoRepository endRepo;
    @Autowired PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "erro", defaultValue = "false") boolean erro) {
        return new ModelAndView("login/login").addObject("erro", erro);
    }
    
    @GetMapping("/cadastro") 
    public ModelAndView cadastroForm() {
        // Endereco endereco = new Endereco();
        // endereco.setPessoa(new Pessoa());
        
        return new ModelAndView("login/cadastro")
                .addObject("pessoa", new Pessoa())
                .addObject("endereco", new Endereco());
    }
    
    @PostMapping("/cadastro")
    public ModelAndView cadastroCreate(
            @ModelAttribute("pessoa") @Valid Pessoa pessoa, BindingResult resultPessoa,
            @ModelAttribute("endereco") @Valid Endereco endereco, BindingResult resultEndereco, 
            RedirectAttributes redirect) {
        
        if (resultPessoa.hasErrors() || resultEndereco.hasErrors()) {
            return new ModelAndView("login/cadastro");
        }
        
        pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
        pessoa = pessRepo.save(pessoa);
        
        endereco.setPessoa(pessoa);
        endereco = endRepo.save(endereco);
        
        redirect.addFlashAttribute("mensagem", "Usuário cadastrado! Você pode pode se logar agora.");
        return new ModelAndView("redirect:/login");
    }
}