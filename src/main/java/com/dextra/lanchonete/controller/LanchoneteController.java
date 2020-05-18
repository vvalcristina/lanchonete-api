package com.dextra.lanchonete.controller;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.service.LanchoneteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lanchonete")
public class LanchoneteController {

    @Autowired
    private LanchoneteService lanchoneteService;

    @GetMapping("/pedido")
    public ResponseEntity<Lanche> criarLanche(@RequestBody Lanche lanche){
        Lanche novoLanche = lanchoneteService.calcularPedido(lanche);
        return ResponseEntity.ok(novoLanche);
    }

    @GetMapping("/pedido/{tipo}")
    public ResponseEntity<Lanche> calcularPreco(@PathVariable("tipo")TipoLanche tipoLanche){
        Lanche lanche = lanchoneteService.precoLanche(tipoLanche);
        return ResponseEntity.ok(lanche);
    }
}
