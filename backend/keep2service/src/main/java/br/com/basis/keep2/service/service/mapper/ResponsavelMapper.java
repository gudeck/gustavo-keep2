package br.com.basis.keep2.service.service.mapper;

import br.com.basis.keep2.service.domain.Responsavel;
import br.com.basis.keep2.service.service.dto.ResponsavelDTO;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper extends EntityMapper<ResponsavelDTO, Responsavel> {
}
