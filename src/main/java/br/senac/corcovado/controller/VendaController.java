package br.senac.corcovado.controller;

import br.senac.corcovado.Utils;
import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Pagamento;
import br.senac.corcovado.model.entity.Produto;
import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.repository.EnderecoRepository;
import br.senac.corcovado.model.repository.PessoaRepository;
import br.senac.corcovado.model.repository.ProdutoRepository;
import br.senac.corcovado.model.repository.VendaRepository;
import br.senac.corcovado.model.service.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author wesley
 */
@Controller
public class VendaController {    
    @Autowired 
    private VendaRepository vendRepo;
    @Autowired ProdutoRepository prodRepo;
    
    @GetMapping("/vendas")
    public ModelAndView list() {        
        ModelAndView mav = new ModelAndView("/venda/venda_list");
        mav.addObject("vendas", vendRepo.findAllByStatusNot(Status.RASCUNHO));
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping("/vendas/{id}")
    public ModelAndView show(@PathVariable("id") String usId) {
        ModelAndView mav = new ModelAndView("/venda/venda_show");
        mav.addObject("venda", vendRepo.findById(Long.parseLong(usId)).get())
                .addObject("ended", vendRepo.findById(Long.parseLong(usId)).get().getStatus() == Status.ENCERRADO);
                //.addObject("auth", Utils.getAuth());
        return mav;
    }
    
    @GetMapping({"/vendas/{id}/edit", "/vendas/edit/{id}" })
    public ModelAndView edit(@PathVariable("id") long id) {
        return form("update")
                .addObject("venda", vendRepo.findById(id).get());
    }

    @PostMapping(path = "/vendas/update")
    public ModelAndView update(@Valid @ModelAttribute Venda venda,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return form("update");
        }
        Venda oldVenda = vendRepo.findById(venda.getId()).get();
        
        oldVenda.setStatus(venda.getStatus());
        oldVenda.setPagamento(venda.getPagamento());
        oldVenda.setComprovante(venda.getComprovante());
        oldVenda.setCodigoRastreamento(venda.getCodigoRastreamento());
        oldVenda.setPrazoEntrega(venda.getPrazoEntrega());
        
        Venda salvo = vendRepo.save(oldVenda);
        //redirectAttributes.addFlashAttribute("mensagem", "Venda alterada com sucesso!!!");
        return new ModelAndView("redirect:" + salvo.getId());
    }

    @PostMapping(path = { "/vendas/{id}/destroy", "/vendas/destroy/{id}" })
    public ModelAndView destroy(@PathVariable("id") long id) {
        vendRepo.deleteById(id);
        return new ModelAndView("redirect:/vendas");
    }

    private ModelAndView form(String action) {
        return new ModelAndView("/venda/venda_form")
                .addObject("action", action)
                .addObject("statuses", Status.values())
                .addObject("pagamentos", Pagamento.values());
    }
    
    @PostMapping(path = { "/vendas/{id}/end", "/vendas/end/{id}" })
    public ModelAndView encerra(@PathVariable("id") long id) {
        Venda venda = vendRepo.findById(id).get();
        venda.setStatus(Status.ENCERRADO);
        for(ProdutoVendido pv : venda.getProdutoVendidos()) {
            Produto produto = pv.getProduto();
            produto.setEstoque( (produto.getEstoque() - pv.getQuantidade()) );
            produto.setReservado( (produto.getReservado()- pv.getQuantidade()) );
            prodRepo.save(produto);
        }
        
        return new ModelAndView("redirect:/vendas");
    }
    
    @Autowired private AuthService authServ;
    @ModelAttribute("auth")
    public Auth getAuth() {
        return authServ.getCurrentUser();
    }
}
