package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Comentario;
import br.com.basis.keep2.service.service.dto.ComentarioDTO;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper extends EntityMapper<ComentarioDTO, Comentario> {

    @Override
    @InheritInverseConfiguration
    Comentario toEntity(ComentarioDTO dto);

    @Override
    @Mapping(target = "idTarefa", source = "tarefa.id")
    ComentarioDTO toDto(Comentario entity);

}
