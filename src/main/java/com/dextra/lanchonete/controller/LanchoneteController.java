package com.dextra.lanchonete.controller;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.service.LanchoneteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class LanchoneteController {

    @Autowired
    private LanchoneteService lanchoneteService;

    private  Lanche lanche;

    @GetMapping("/lanchonete")
    public List<Lanche> getAllLanche(){
        return lanchoneteService.findAll();
    }

    @GetMapping("/lanchonete/lanche/{id}")
    public ResponseEntity<Lanche> getLanche(@PathVariable("id") String  id){
        final Lanche lanche = lanchoneteService.findById(id);
        return ResponseEntity.ok(lanche);
    }

    @PostMapping("/lanchonete/lanche")
    @ResponseStatus(HttpStatus.CREATED)
    public Lanche criarLanche(@Valid @RequestBody Lanche lanche){
       return lanchoneteService.save(lanche);
    }

    @GetMapping("/lanchonete/{tipo}")
    public ResponseEntity<Lanche> calcularPreco(@PathVariable("tipo")TipoLanche tipoLanche){
        Lanche lanche = lanchoneteService.precoLanche(tipoLanche);
        return ResponseEntity.ok(lanche);
    }

    @DeleteMapping("/lanchonete/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable String id){
        lanchoneteService.delete(id);
        return ResponseEntity.ok(String.format("Lanche deletado"));
    }



}
