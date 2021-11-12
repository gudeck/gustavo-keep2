package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.service.dto.TarefaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(target = "idResponsavel", source = "responsavel.id")
    TarefaDTO toDto(Tarefa entity);

    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDTO dto);

}
