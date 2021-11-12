package br.com.basis.keep2.service.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    private Long id;
    private Long idTarefa;
    private String conteudo;

}
