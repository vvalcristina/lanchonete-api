package com.dextra.lanchonete.controller;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    private  Lanche lanche;

    @GetMapping
    public List<Lanche> getAllLanche(){
        return pedidosService.findAll();
    }

    @GetMapping("/lanche/{id}")
    public ResponseEntity<Lanche> getLanche(@PathVariable("id") String  id){
        final Lanche lanche = pedidosService.findById(id);
        return ResponseEntity.ok(lanche);
    }

    @PostMapping("/lanche")
    @ResponseStatus(HttpStatus.CREATED)
    public Lanche criarLanche(@Valid @RequestBody Lanche lanche){
       return pedidosService.save(lanche);
    }

    @GetMapping("/pedido/{tipo}")
    public ResponseEntity<Lanche> calcularPreco(@PathVariable("tipo")TipoLanche tipoLanche){
        Lanche lanche = pedidosService.precoLanche(tipoLanche);
        return ResponseEntity.ok(lanche);
    }

    @DeleteMapping("/lanche/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        pedidosService.delete(id);
    }

}
