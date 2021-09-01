package br.edu.utfpr.pb.pw25s.controller;

import br.edu.utfpr.pb.pw25s.model.Cliente;
import br.edu.utfpr.pb.pw25s.model.Homepage;
import br.edu.utfpr.pb.pw25s.model.Produto;
import br.edu.utfpr.pb.pw25s.model.enumerators.GeneroFilme;
import br.edu.utfpr.pb.pw25s.repository.HomepageRepository;
import br.edu.utfpr.pb.pw25s.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class HomepageController extends BasicController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private HomepageRepository homepageRepository;

    @GetMapping
    public String mainPage(Model model) {
        return redirectToHome(model);
    }

    @GetMapping("inicio")
    public String inicio(Model model) {
        return redirectToHome(model);
    }

    @GetMapping("home")
    public String home(Model model) {
        return redirectToHome(model);
    }

    @GetMapping("start")
    public String start(Model model) {
        return redirectToHome(model);
    }

    private String redirectToHome(Model model) {
        model.addAttribute("homepageData", getLatestHome());
        model.addAttribute("produtosDestaque", getProdutosDestaque());
        model.addAttribute("generoFilmes", getGeneroFilmes());
        return "index";
    }

    @ModelAttribute("homepageData")
    public Homepage getLatestHome() {
        return homepageRepository.findTopByOrderByIdDesc();
    }

    @ModelAttribute("produtosDestaque")
    public List<Produto> getProdutosDestaque() {
        List<Produto> produtoDestaque = new ArrayList<>();
        int totalProdutos = (int) produtoService.count();
        int randomIndex = (int) (Math.random() * totalProdutos);

        if (randomIndex + 2 > totalProdutos - 1) {
            randomIndex = 0;
        }

        // Adiciona três produtos randomizados
        produtoDestaque.addAll(produtoService.findAll(PageRequest.of(randomIndex, 3)).getContent());

        return produtoDestaque;
    }

}
