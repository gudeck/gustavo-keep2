package br.com.basis.keep2.mail.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    private String destinatario;
    private String assunto;
    private String conteudo;

}
