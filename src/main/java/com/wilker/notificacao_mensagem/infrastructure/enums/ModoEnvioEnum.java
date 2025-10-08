package com.wilker.notificacao_mensagem.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ModoEnvioEnum {
    EMAIL,
    SMS,
    PUSH,
    WHATSAPP;
}
