package br.edu.utfpr.pb.pw25s.controller;

import br.edu.utfpr.pb.pw25s.model.Cliente;
import br.edu.utfpr.pb.pw25s.model.Compra;
import br.edu.utfpr.pb.pw25s.model.enumerators.Estado;
import br.edu.utfpr.pb.pw25s.repository.CompraRepository;
import br.edu.utfpr.pb.pw25s.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cliente")
public class ClienteController extends BasicController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String cliente(Model model) {
        return this.abrirForm(model, new Cliente());
    }

    @GetMapping("/cadastro")
    public String form(Model model) {
        return this.abrirForm(model, new Cliente());
    }

    @GetMapping("/meus-pedidos")
    public String meusPedidos(Model model) {
        Cliente logado = (Cliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<List<Compra>> comprasCliente = compraRepository.findComprasByClienteIdOrderByIdDesc(logado.getId());

        if (comprasCliente.isPresent() && comprasCliente.get().size() > 0) {
            model.addAttribute("compras", comprasCliente.get());
            return "cliente/list-pedidos";
        }

        model.addAttribute("msgErro", "Nenhum pedido encontrado!");
        return "layout/layout-erro";
    }

    @PostMapping
    public String save(
            @Valid Cliente cliente,
            BindingResult result,
            Model model,
            RedirectAttributes attributes
    ) {
        if (result.hasErrors()) {
            return this.abrirForm(model, cliente);
        }

        try {
            // cria um hash da senha e da confirmação
            String hashSenha = passwordEncoder.encode(cliente.getSenha());
            cliente.setSenha(hashSenha);
            cliente.setConfirmacaoSenha(hashSenha);

            // salva o registro
            clienteService.save(cliente);
            attributes.addFlashAttribute("msgSucesso", "Cadastro efetuado com sucesso!");

            // redireciona para a tela de login
            return "redirect:login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            attributes.addFlashAttribute("erro", "Não foi possível salvar o registro!");
        }

        return this.abrirForm(model, cliente);
    }

    /**
     * @param model   Model do template
     * @param cliente Cliente que estará sendo editado
     * @description Retorna o template do form inserindo o cliente no model
     */
    private String abrirForm(Model model, Cliente cliente) {
        model.addAttribute("cliente", cliente);
        model.addAttribute("estados", getEstados());
        return "cliente/form";
    }

    @ModelAttribute("estados")
    public Estado[] getEstados() {
        return Estado.values();
    }

}
