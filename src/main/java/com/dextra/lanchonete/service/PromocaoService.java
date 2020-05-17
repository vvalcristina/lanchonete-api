package com.dextra.lanchonete.service;

import com.dextra.lanchonete.model.enums.TipoIngrediente;
import com.dextra.lanchonete.repository.LanchoneteRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromocaoService {

    @Autowired
    LanchoneteRepository lanchoneteRepository;

    TipoIngrediente tipoIngrediente;

    LanchoneteService lanchoneteService;

    //Lanche tem alface e bacon: 10% de desconto
    public BigDecimal promocaoLight(List<TipoIngrediente> ingrediente, BigDecimal valor){
        BigDecimal desconto = BigDecimal.ZERO;
        boolean alface = false;
        boolean bacon = false;

        for(TipoIngrediente ingredienteTipo: ingrediente){
            if (ingredienteTipo == TipoIngrediente.ALFACE){
                alface = true;
            }
            if (ingredienteTipo == TipoIngrediente.BACON){
                bacon = true;
            }
        }
        if(alface && !bacon){
            desconto= valor.multiply(new BigDecimal(10)).divide(new BigDecimal(100));
        }
        return valor.subtract(desconto);
    }

    //A cada 3 porções de carne o cliente paga 2.
    //Se o lanche tiver 6 porções o cliente paga 4 e assim suscessivamente
    //Queijo é a mesma coisa
    public BigDecimal promocaoCarneQueijo(List<TipoIngrediente> ingrediente, BigDecimal valor){
        int carne = 0;
        int queijo = 0;

        for (TipoIngrediente ingredienteTipo: ingrediente){
            if (ingredienteTipo == TipoIngrediente.HAMBURGUER){
                ++carne;
            }
            if (ingredienteTipo == TipoIngrediente.QUEIJO){
                ++queijo;
            }
        }
        BigDecimal carneDesconto = new BigDecimal(carne/3).setScale(1, RoundingMode.DOWN);
        BigDecimal queijoDesconto = new BigDecimal(queijo/3).setScale(1, RoundingMode.DOWN);

        BigDecimal desconto = lanchoneteService.precoIngrediente(TipoIngrediente.HAMBURGUER).multiply(carneDesconto)
                .add(lanchoneteService.precoIngrediente(TipoIngrediente.QUEIJO).multiply(queijoDesconto));

        return valor.subtract(desconto);
    }
}
