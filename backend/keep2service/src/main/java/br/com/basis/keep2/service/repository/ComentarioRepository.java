package br.com.basis.keep2.service.repository;

import br.com.basis.keep2.service.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>, JpaSpecificationExecutor<Comentario> {

    List<Comentario> findAllByTarefaId(Long idTarefa);
}
