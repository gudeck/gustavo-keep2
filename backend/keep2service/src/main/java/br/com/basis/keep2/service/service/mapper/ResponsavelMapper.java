package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Responsavel;
import br.com.basis.keep2.service.service.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {
    ResponsavelDTO toDto(Responsavel entity);

    Responsavel toEntity(ResponsavelDTO dto);
}
