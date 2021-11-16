package br.com.basis.keep2.service.service.event;

import br.com.basis.keep2.service.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TarefaMailEvent extends BaseEvent {
    public TarefaMailEvent(Long id) {
        super(id);
    }
}
