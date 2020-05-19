package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.TipoIngrediente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "ingrediente")
public class Ingrediente {

    @Id
    public String id;

    TipoIngrediente ingrediente;

    BigDecimal preco;

    Integer quantidade;



}
