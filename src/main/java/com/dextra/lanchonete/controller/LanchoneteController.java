package com.dextra.lanchonete.controller;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.service.LanchoneteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LanchoneteController {

    @Autowired
    private LanchoneteService lanchoneteService;

    @Autowired
    private  Lanche lanche;

    @PostMapping("/pedido")
    public ResponseEntity<Lanche> criarLanche(@RequestBody Lanche lanche){
        Lanche novoLanche = lanchoneteService.calcularPedido(lanche);
        return ResponseEntity.ok(novoLanche);
    }

    @GetMapping("/pedido/{tipo}")
    public ResponseEntity<Lanche> calcularPreco(@PathVariable("tipo")TipoLanche tipoLanche){
        Lanche lanche = lanchoneteService.precoLanche(tipoLanche);
        return ResponseEntity.ok(lanche);
    }

    public ResponseEntity<String> delete(@PathVariable String id){
        lanchoneteService.delete(id);
        return ResponseEntity.ok(String.format("Lanche deletado"));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
        lanchoneteService.deleteAll();
        return ResponseEntity.ok(String.format("Lanches deletados!"));
    }


}
