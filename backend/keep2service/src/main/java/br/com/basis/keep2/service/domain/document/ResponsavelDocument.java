package br.com.basis.keep2.service.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "responsavel")
public class ResponsavelDocument {

    @Id
    private Long id;

    private String nome;

    private String email;

    @Field(type = Date, format = date)
    private LocalDate dataNascimento;

}
