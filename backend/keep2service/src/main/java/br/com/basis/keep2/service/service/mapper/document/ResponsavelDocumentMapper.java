package br.com.basis.keep2.service.service.mapper.document;

import br.com.basis.keep2.service.domain.Responsavel;
import br.com.basis.keep2.service.domain.document.ResponsavelDocument;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelDocumentMapper extends EntityMapper<ResponsavelDocument, Responsavel> {
}
