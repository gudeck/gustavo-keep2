package br.com.basis.keep2.service.service.dto;

import br.com.basis.keep2.service.domain.enumeration.Tipo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaDTO {

    private Long id;
    private Long idResponsavel;
    private String titulo;
    private String descricao;
    private Tipo tipo;
    private LocalDate dataFimPrevista;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
