package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adicional {

    public TipoIngrediente tipoIngrediente;

    public Integer quantidade;
}
