package com.wilker.notificacao_mensagem.service;


import com.wilker.notificacao_mensagem.infrastructure.dto.ComunicacaoOutDTO;
import com.wilker.notificacao_mensagem.infrastructure.enums.StatusEnvioEnum;
import com.wilker.notificacao_mensagem.infrastructure.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${envio.email.remetente}")
    public String remetente;

    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;


    public void enviarEmail(ComunicacaoOutDTO comunicacaoOutDTO){
        try{

            MimeMessage mensagem = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));

            mimeMessageHelper.setTo(InternetAddress.parse(comunicacaoOutDTO.getEmailDestinatario()));

            mimeMessageHelper.setSubject("Notificação de Mensagem");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    .withLocale(new Locale("pt", "BR"));

            Context context = new Context();
            context.setVariable("destinatario", comunicacaoOutDTO.getNomeDestinatario());
            context.setVariable("mensagem", comunicacaoOutDTO.getMensagem());

            String template = templateEngine.process("notificacao", context);

            mimeMessageHelper.setText(template, true);

            javaMailSender.send(mensagem);

            comunicacaoOutDTO.setStatusEnvio(StatusEnvioEnum.ENVIADO);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o email" + e.getCause());
        }
    }
}


