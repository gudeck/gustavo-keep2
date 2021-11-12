package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Comentario;
import br.com.basis.keep2.service.service.dto.ComentarioDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "idTarefa", source = "tarefa.id")
    ComentarioDTO toDto(Comentario entity);

    @InheritInverseConfiguration
    Comentario toEntity(ComentarioDTO dto);

}
