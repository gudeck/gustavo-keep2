package br.com.basis.keep2.service.repository;

import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.domain.enumeration.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findAllBySituacao(Situacao situacao);
}
