package br.edu.utfpr.pb.pw25s.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CompanyMailer {

    public CompanyMailer() {
    }

    public EmailMensagem montarEmailRecuperacaoSenha(String path, String token, String clienteEmail) {
        String url = path + "/login/trocar-senha?token=" + token;
        String mensagem = "Clique no link para recuperar sua senha.\nO link irá expirar em 1 hora" + "\r\n" + url;
        return new EmailMensagem("Recuperação de senha", mensagem, clienteEmail);
    }

    public void enviarEmail(EmailMensagem mensagem) {
        JavaMailSenderImpl sender = getMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("arealdotcompany@gmail.com");
        mailMessage.setTo(mensagem.destinatario);
        mailMessage.setSubject(mensagem.titulo);
        mailMessage.setText(mensagem.corpo);
        sender.send(mailMessage);
    }

    private JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // resources/mail.properties é ignorado pelo git!
        try (InputStream input = new FileInputStream("resources/mail.properties")) {
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            Properties mailProp = new Properties();
            mailProp.load(input);

            mailSender.setUsername(mailProp.getProperty("endereco"));
            mailSender.setPassword(mailProp.getProperty("senha"));

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mailSender;
    }


}
