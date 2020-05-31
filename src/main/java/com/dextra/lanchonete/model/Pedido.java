package com.dextra.lanchonete.model;

import com.dextra.lanchonete.model.enums.TipoLanche;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    private String id;
    private TipoLanche lanche;
    private List<Adicional> adicionais;
    private String total;
}
