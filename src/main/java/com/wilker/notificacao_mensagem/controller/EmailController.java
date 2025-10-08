package com.wilker.notificacao_mensagem.controller;


import com.wilker.notificacao_mensagem.infrastructure.dto.ComunicacaoOutDTO;
import com.wilker.notificacao_mensagem.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestBody ComunicacaoOutDTO comunicacaoOutDTO){
        emailService.enviarEmail(comunicacaoOutDTO);
        String mensagem = "E-mail enviado com sucesso para " + comunicacaoOutDTO.getEmailDestinatario();
        return ResponseEntity.ok(mensagem);
    }

}
