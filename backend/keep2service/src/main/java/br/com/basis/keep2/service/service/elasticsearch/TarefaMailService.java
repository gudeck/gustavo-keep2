package br.com.basis.keep2.service.service.elasticsearch;

import br.com.basis.keep2.service.config.ExchangeConstant;
import br.com.basis.keep2.service.domain.Responsavel;
import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.domain.enumeration.Situacao;
import br.com.basis.keep2.service.repository.ResponsavelRepository;
import br.com.basis.keep2.service.repository.TarefaRepository;
import br.com.basis.keep2.service.service.dto.DummyDTO;
import br.com.basis.keep2.service.service.dto.EmailDTO;
import br.com.basis.keep2.service.service.event.TarefaMailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDate;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class TarefaMailService {

    private final StreamBridge streamBridge;
    private final TarefaRepository tarefaRepository;
    private final ResponsavelRepository responsavelRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void enviarEmailAtribuicao(TarefaMailEvent event) {
        tarefaRepository.findById(event.getId())
            .ifPresent(tarefa -> {
                var email = montarEmailAtribuicao(tarefa);
                streamBridge.send(ExchangeConstant.MAIL, email);
            });
    }

    private EmailDTO montarEmailAtribuicao(Tarefa tarefa) {
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

    @Bean
    public Consumer<DummyDTO> tarefaReminder() {
        return unused -> tarefaRepository.findAllBySituacao(Situacao.ATRIBUIDA)
            .forEach(tarefa -> {
                var email = montarEmailLembrete(tarefa);
                streamBridge.send(ExchangeConstant.MAIL, email);
            });
    }

    private EmailDTO montarEmailLembrete(Tarefa tarefa) {
        var email = new EmailDTO();
        email.setAssunto("Existem tarefas ainda não tramitadas na sua caixa!");
        email.setConteudo(String.format("A tarefa %s ainda não foi tramitada!\n" +
                "Faltam 3 dias para atingir o a data limite.\n" +
                "Descrição:\n%s\n\n" +
                "Se liga hein!",
            tarefa.getTitulo(),
            tarefa.getDescricao()));

        email.setDestinatario(responsavelRepository.findById(tarefa.getResponsavel().getId()).map(Responsavel::getEmail).get());
        return email;
    }

}
