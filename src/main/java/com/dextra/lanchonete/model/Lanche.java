package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.Ingrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "lanche")
public class Lanche {

    @Id
    private String id;

    private String descricao;
    private TipoLanche tipoLanche;
    private String preco;

    public List<Adicional> adicionais;

    public static Collection<? extends Ingrediente> ingredientes(TipoLanche tipoLanche){
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

    public List<Adicional> getAdicional(){return adicionais;}

}
