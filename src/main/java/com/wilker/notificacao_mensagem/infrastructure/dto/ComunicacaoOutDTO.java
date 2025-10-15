package com.wilker.notificacao_mensagem.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilker.notificacao_mensagem.infrastructure.enums.ModoEnvioEnum;
import com.wilker.notificacao_mensagem.infrastructure.enums.StatusEnvioEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ComunicacaoOutDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraEnvio;
    private String nomeDestinatario;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private ModoEnvioEnum modoDeEnvio;
    private StatusEnvioEnum statusEnvio;

}
