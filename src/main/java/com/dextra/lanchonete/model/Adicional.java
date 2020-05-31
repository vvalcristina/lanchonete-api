package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.Ingrediente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "adicional")
public class Adicional {

    private Ingrediente ingrediente;
    private Integer quantidade;
}
