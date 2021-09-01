package br.edu.utfpr.pb.pw25s.controller;

import br.edu.utfpr.pb.pw25s.model.enumerators.GeneroFilme;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BasicController {

    @ModelAttribute("generoFilmes")
    public GeneroFilme[] getGeneroFilmes() { return GeneroFilme.values(); }

}
