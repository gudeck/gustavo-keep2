package br.com.basis.keep2.service.service.event;

import br.com.basis.keep2.service.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponsavelEvent extends BaseEvent {
    public ResponsavelEvent(Long id) {
        super(id);
    }
}
