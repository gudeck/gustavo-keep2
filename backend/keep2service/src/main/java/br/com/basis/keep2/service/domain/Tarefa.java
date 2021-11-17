package br.com.basis.keep2.service.domain;

import br.com.basis.keep2.service.domain.enumeration.Situacao;
import br.com.basis.keep2.service.domain.enumeration.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator_tarefa")
    @SequenceGenerator(name = "seq_generator_tarefa", sequenceName = "seq_tarefa", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "id_responsavel")
    @ManyToOne(fetch = LAZY, optional = false)
    private Responsavel responsavel;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Situacao situacao = Situacao.ATRIBUIDA;

    @Column(nullable = false)
    private LocalDate dataFimPrevista;

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
