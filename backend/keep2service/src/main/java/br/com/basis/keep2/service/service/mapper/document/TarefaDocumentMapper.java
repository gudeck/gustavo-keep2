package br.com.basis.keep2.service.service.mapper.document;

import br.com.basis.keep2.service.domain.Tarefa;
import br.com.basis.keep2.service.domain.document.TarefaDocument;
import br.gov.nuvem.comum.microsservico.service.reindex.mapper.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaDocumentMapper extends EntityMapper<TarefaDocument, Tarefa> {

    @Mapping(target = "responsavel.id", source = "idResponsavel")
    @Mapping(target = "responsavel.nome", source = "responsavel")
    @Override
    Tarefa toEntity(TarefaDocument tarefaDocument);

    @Override
    @InheritInverseConfiguration
    TarefaDocument toDto(Tarefa tarefa);
}
