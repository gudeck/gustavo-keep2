package br.com.basis.keep2.batch.service;

import br.com.basis.keep2.batch.config.ExchangeConstant;
import br.com.basis.keep2.batch.service.dto.DummyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final StreamBridge streamBridge;

    @Scheduled(cron = "0/30 */2 * * * *")
    public void lembreteEmail() {
        streamBridge.send(ExchangeConstant.TAREFA_REMINDER, new DummyDTO());
    }

}
