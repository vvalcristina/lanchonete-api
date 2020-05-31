package com.dextra.lanchonete.service;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.Ingrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;


public class PedidosServiceTest {

    @Mock
    private PedidosService pedidosService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockIngredientes();
    }

    private void mockIngredientes() {
        Mockito.when(pedidosService.precoIngrediente(Ingrediente.ALFACE)).thenReturn(new BigDecimal(0.40));
        Mockito.when(pedidosService.precoIngrediente(Ingrediente.BACON)).thenReturn(new BigDecimal(2.00));
        Mockito.when(pedidosService.precoIngrediente(Ingrediente.HAMBURGUER)).thenReturn(new BigDecimal(3.00));
        Mockito.when(pedidosService.precoIngrediente(Ingrediente.OVO)).thenReturn(new BigDecimal(0.80));
        Mockito.when(pedidosService.precoIngrediente(Ingrediente.QUEIJO)).thenReturn(new BigDecimal(1.50));
    }

    @Test
    public void precoLanche(){
        TipoLanche xBacon = TipoLanche.X_BACON;
        TipoLanche xEgg = TipoLanche.X_EGG;
        TipoLanche xBaconEgg = TipoLanche.X_EGG_BACON;
        TipoLanche xBurguer = TipoLanche.X_BURGUER;

        PedidosService pedidosService = new PedidosService();

        BigDecimal xBaconPreco = new BigDecimal(6.50);
        Lanche lanche = pedidosService.precoLanche(xBacon);
        Assertions.assertNotNull(lanche);
        Assertions.assertEquals(xBaconPreco, lanche);

        BigDecimal xEggPreco = new BigDecimal(5.30);
        lanche = pedidosService.precoLanche(xEgg);
        Assertions.assertNotNull(lanche);
        Assertions.assertEquals(xEggPreco, xEgg);

        BigDecimal xBaconEggPreco = new BigDecimal(7.30);
        lanche = pedidosService.precoLanche(xBaconEgg);
        Assertions.assertNotNull(lanche);
        Assertions.assertEquals(xBaconEggPreco, xBaconEgg);

        BigDecimal xBurguerPreco = new BigDecimal(4.50);
        lanche = pedidosService.precoLanche(xBurguer);
        Assertions.assertNotNull(lanche);
        Assertions.assertEquals(xBurguerPreco, xBurguer);

    }



}
