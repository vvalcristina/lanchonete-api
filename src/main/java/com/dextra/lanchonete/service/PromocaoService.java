package com.dextra.lanchonete.service;

import com.dextra.lanchonete.model.enums.Ingrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.repository.LanchoneteRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromocaoService {

    @Autowired
    LanchoneteRepository lanchoneteRepository;

    Ingrediente tipoIngrediente;

    PedidosService pedidosService;

    //Lanche tem alface e bacon: 10% de desconto
    public BigDecimal promocaoLight(List<Ingrediente> ingrediente, BigDecimal valor){
        BigDecimal desconto = BigDecimal.ZERO;
        boolean alface = false;
        boolean bacon = false;

        for(Ingrediente ingredienteTipo: ingrediente){
            if (ingredienteTipo == Ingrediente.ALFACE){
                alface = true;
            }
            if (ingredienteTipo == Ingrediente.BACON){
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
    public BigDecimal promocaoCarneQueijo(List<Ingrediente> ingrediente, BigDecimal valor){
        int carne = 0;
        int queijo = 0;

        for (Ingrediente ingredienteTipo: ingrediente){
            if (ingredienteTipo == Ingrediente.HAMBURGUER){
                ++carne;
            }
            if (ingredienteTipo == Ingrediente.QUEIJO){
                ++queijo;
            }
        }
        BigDecimal carneDesconto = new BigDecimal(carne/3).setScale(1, RoundingMode.DOWN);
        BigDecimal queijoDesconto = new BigDecimal(queijo/3).setScale(1, RoundingMode.DOWN);

        BigDecimal desconto = pedidosService.precoIngrediente(Ingrediente.HAMBURGUER).multiply(carneDesconto)
                .add(pedidosService.precoIngrediente(Ingrediente.QUEIJO).multiply(queijoDesconto));

        return valor.subtract(desconto);
    }

    public static List<Ingrediente> ingredientes(TipoLanche tipoLanche){
        switch (tipoLanche){
            case X_BACON:
                return Arrays.asList(Ingrediente.BACON, Ingrediente.HAMBURGUER, Ingrediente.QUEIJO);
            case X_EGG:
                return Arrays.asList(Ingrediente.OVO, Ingrediente.HAMBURGUER, Ingrediente.QUEIJO);
            case X_BURGUER:
                return Arrays.asList(Ingrediente.HAMBURGUER, Ingrediente.QUEIJO);
            case X_EGG_BACON:
                return Arrays.asList(Ingrediente.BACON, Ingrediente.OVO, Ingrediente.HAMBURGUER, Ingrediente.QUEIJO);
        }
        return new ArrayList<Ingrediente>();
    }
}
