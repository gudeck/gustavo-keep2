package br.com.basis.keep2.service.service.elasticsearch;

import br.com.basis.keep2.service.config.ExchangeConstant;
import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.repository.ResponsavelRepository;
import br.com.basis.keep2.service.repository.TarefaRepository;
import br.com.basis.keep2.service.service.dto.EmailDTO;
import br.com.basis.keep2.service.service.event.TarefaMailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TarefaMailService {

    private final StreamBridge streamBridge;
    private final TarefaRepository tarefaRepository;
    private final ResponsavelRepository responsavelRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void enviarEmail(TarefaMailEvent event) {
        tarefaRepository.findById(event.getId())
            .ifPresent(tarefa -> {
                var email = montarEmail(tarefa);
                streamBridge.send(ExchangeConstant.MAIL, email);
            });
    }

    private EmailDTO montarEmail(Tarefa tarefa) {
        var email = new EmailDTO();
        email.setAssunto("Nova tarefa vinculada");
        email.setConteudo(String.format("A tarefa \"%s\" foi vinculada ao seu usuário no dia %s e tem prazo limite %s.\n" +
                "Descrição:\n%s\n\n" +
                "Se liga hein!",
            tarefa.getTitulo(),
            LocalDate.now(),
            tarefa.getDataFimPrevista(),
            tarefa.getDescricao()));
        email.setDestinatario(responsavelRepository.getOne(tarefa.getResponsavel().getId()).getEmail());
        return email;
    }

}
