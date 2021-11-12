package br.com.basis.keep2.service.repository;

import br.com.basis.keep2.service.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
