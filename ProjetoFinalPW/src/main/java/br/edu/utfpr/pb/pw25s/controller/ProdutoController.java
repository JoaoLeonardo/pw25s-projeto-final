package br.edu.utfpr.pb.pw25s.controller;

import br.edu.utfpr.pb.pw25s.model.Produto;
import br.edu.utfpr.pb.pw25s.model.enumerators.GeneroFilme;
import br.edu.utfpr.pb.pw25s.repository.ProdutoRepository;
import br.edu.utfpr.pb.pw25s.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("produto")
public class ProdutoController extends BasicController {
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/interno/{id}")
	public String form(@PathVariable Long id, Model model) {
		model.addAttribute("produto", produtoService.findOne(id));
		model.addAttribute("generoFilmes", getGeneroFilmes());
		return "produto/form";
	}

	@GetMapping("/{path}")
	public String listGenero(@PathVariable String path, Model model) {
		GeneroFilme genero = GeneroFilme.getByPath(path);
		model.addAttribute("generoNome", genero.getDescricao());
		model.addAttribute("produtosGenero", getProdutosGenero(genero));
		model.addAttribute("generoFilmes", getGeneroFilmes());
		return "produto/list";
	}

	@ModelAttribute("produtosGenero")
	public List<Produto> getProdutosGenero(GeneroFilme genero) {
		return produtoRepository.findByGenero(genero);
	}

}
