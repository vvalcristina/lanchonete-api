package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "adicional")
public class Adicional {

    public TipoIngrediente tipoIngrediente;

    public Integer quantidade;
}
