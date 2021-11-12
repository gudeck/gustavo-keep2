package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.service.dto.TarefaDTO;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDTO dto);

    @Override
    @Mapping(target = "idResponsavel", source = "responsavel.id")
    TarefaDTO toDto(Tarefa entity);

}
