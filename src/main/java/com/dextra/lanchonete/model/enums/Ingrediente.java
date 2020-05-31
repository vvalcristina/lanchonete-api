package com.dextra.lanchonete.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Ingrediente {

    ALFACE,
    BACON,
    HAMBURGUER,
    OVO,
    QUEIJO;

    public static String ingrediente (Ingrediente tipoIngrediente){
        switch (tipoIngrediente){
            case ALFACE:
                return "Alface";
            case BACON:
                return "Bacon";
            case HAMBURGUER:
                return "Hamburguer de carne";
            case OVO:
                return "Ovo";
            case QUEIJO:
                return "Queijo";
            default:
                break;
        }
        return null;
    }

}
