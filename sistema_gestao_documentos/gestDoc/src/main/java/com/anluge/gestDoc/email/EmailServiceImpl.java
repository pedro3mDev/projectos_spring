package com.anluge.gestDoc.email;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.anluge.gestDoc.Application;
import com.anluge.gestDoc.entitys.Usuario;
import com.anluge.gestDoc.utils.UrlMapping;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Application app;

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailManager;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private UrlMapping urlAtivarConta;

    @Autowired
    private UrlMapping urlResetSenha;

    @Override
    public void dispararEmailAtivacaoUsuario(Usuario u) {
        final Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("email", u.getEmail());
        ctx.setVariable("token", u.getToken());
        ctx.setVariable("linkAtivacao", app.getDomain() + urlAtivarConta.getUrlBuilder().build(u.getEmail(), u.getToken()));
        this.dispararEmail(this.templateEngine.process("mail/ativacao-usuario.html", ctx), "Ative sua conta!", u.getEmail());
    }

    @Override
    public void dispararEmailRedefinirSenha(Usuario u) {
        final Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("email", u.getEmail());
        ctx.setVariable("token", u.getToken());
        ctx.setVariable("linkAtivacao", app.getDomain() + urlResetSenha.getUrlBuilder().build(u.getEmail(), u.getToken()));
        this.dispararEmail(this.templateEngine.process("mail/redefinir-senha.html", ctx), "solitação de redifinição de senha",
            u.getEmail());
    }

    private void dispararEmail(String html, String titulo, String destinatario) {
        try {
            final MimeMessage mimeMessage = mailManager.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(titulo);
            message.setFrom(env.getProperty("spring.mail.username"));
            message.setTo(destinatario);
            message.setText(html, true);
            mailManager.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}