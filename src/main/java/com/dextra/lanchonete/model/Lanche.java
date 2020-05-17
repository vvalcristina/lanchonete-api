package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.TipoIngrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collation = "lanches")
public class Lanche {

    @Id
    public Long id;

    public TipoLanche tipoLanche;

    BigDecimal precoLanche;

    public List<Adicional> adicionais;

    public static List<TipoIngrediente> ingredientes(TipoLanche tipoLanche){
        switch (tipoLanche){
            case X_BACON:
                return Arrays.asList(TipoIngrediente.BACON, TipoIngrediente.HAMBURGUER, TipoIngrediente.QUEIJO);
            case X_EGG:
                return Arrays.asList(TipoIngrediente.OVO, TipoIngrediente.HAMBURGUER,TipoIngrediente.QUEIJO);
            case X_BURGUER:
                return Arrays.asList(TipoIngrediente.HAMBURGUER, TipoIngrediente.QUEIJO);
            case X_EGG_BACON:
                return Arrays.asList(TipoIngrediente.BACON, TipoIngrediente.OVO, TipoIngrediente.HAMBURGUER, TipoIngrediente.QUEIJO);
        }
        return new ArrayList<TipoIngrediente>();
    }

    public List<Adicional> getAdicional(){return adicionais;}

}