package br.com.basis.keep2.service.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

}
