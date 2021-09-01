package br.edu.utfpr.pb.pw25s.controller;

import br.edu.utfpr.pb.pw25s.model.Cliente;
import br.edu.utfpr.pb.pw25s.model.SenhaResetToken;
import br.edu.utfpr.pb.pw25s.model.SenhaTrocaDto;
import br.edu.utfpr.pb.pw25s.repository.ClienteRepository;
import br.edu.utfpr.pb.pw25s.service.impl.ClienteServiceImpl;
import br.edu.utfpr.pb.pw25s.service.impl.SenhaResetTokenServiceImpl;
import br.edu.utfpr.pb.pw25s.util.CompanyMailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("login")
public class LoginController extends BasicController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    SenhaResetTokenServiceImpl senhaResetTokenService;

    @GetMapping()
    public String login() {
        return "login/form";
    }

    @GetMapping("recuperar-senha")
    public String recuperarSenha() {
        return "login/recuperar-senha";
    }

    @PostMapping("recuperar-senha-enviar-email")
    public ResponseEntity<String> recuperarSenhaEnviarEmail(
            HttpServletRequest request,
            @RequestParam("email") String clienteEmail
    ) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(clienteEmail);

        if (cliente.isPresent()) {
            String token = UUID.randomUUID().toString();
            senhaResetTokenService.createResetSenhaToken(cliente.get(), token);
            CompanyMailer mailer = new CompanyMailer();
            String baseUrl = String.format(
                    "%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort()
            );

            mailer.enviarEmail(mailer.montarEmailRecuperacaoSenha(baseUrl, token, clienteEmail));
        }

        return new ResponseEntity<String>("Request pronto", HttpStatus.OK);
    }

    @GetMapping("trocar-senha")
    public String trocarSenha(@RequestParam("token") String token, Model model) {
        if (model.getAttribute("trocaSenha") == null) {
            SenhaResetToken resetToken = senhaResetTokenService.validarSenhaResetToken(token);

            if (resetToken != null) {
                SenhaTrocaDto dto = new SenhaTrocaDto();
                dto.setToken(token);
                model.addAttribute("trocaSenha", dto);
                return "login/trocar-senha";
            } else {
                model.addAttribute("msgErro", "Token inválido!");
                return "layout/layout-erro";
            }
        }

        return "login/trocar-senha";
    }

    @RequestMapping(path = "/trocar-senha", method = RequestMethod.POST)
    public String trocarSenhaPost(
            SenhaTrocaDto trocaSenha,
            Model model,
            RedirectAttributes attributes
    ) {
        if (!trocaSenha.getSenha().equals(trocaSenha.getConfirmacaoSenha())) {
            model.addAttribute("msgErro", "As senhas não coincidem!");
            return trocarSenha(trocaSenha.getToken(), model);
        }

        SenhaResetToken token = senhaResetTokenService.validarSenhaResetToken(trocaSenha.getToken());

        if (token != null) {
            try {
                Cliente cliente = token.getCliente();
                clienteService.resetSenha(cliente, trocaSenha.getSenha());

                // redireciona para a tela de login
                attributes.addFlashAttribute("msgSucesso", "A senha foi atualizada com sucesso!");
                return "redirect:/login";
            } catch (Exception e) {
                model.addAttribute("msgErro", "Não foi possível concluir a operação!");
                model.addAttribute("trocaSenha", trocaSenha);
                return trocarSenha(trocaSenha.getToken(), model);
            }
        }

        attributes.addFlashAttribute("msgErro", "Token inválido!");
        model.addAttribute(trocaSenha.getToken(), model);
        return trocarSenha(null, model);
    }


}
