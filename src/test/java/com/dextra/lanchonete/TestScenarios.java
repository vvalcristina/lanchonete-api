package com.dextra.lanchonete;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestScenarios {


    public mockLanche(){
        Lanche.ingredientes(TipoLanche.X_BACON);
    }
}
