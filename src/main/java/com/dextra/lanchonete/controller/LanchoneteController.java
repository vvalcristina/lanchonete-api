package com.dextra.lanchonete.controller;

import com.dextra.lanchonete.exception.LancheNotFoundException;
import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.repository.LanchoneteRepository;
import com.dextra.lanchonete.service.LanchoneteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LanchoneteController {

    @Autowired
    private LanchoneteService lanchoneteService;

    @Autowired
    private LanchoneteRepository lanchoneteRepository;

    private  Lanche lanche;

    @GetMapping("/lanche")
    public List<Lanche> getAllLanche(){
        return lanchoneteRepository.findAll();
    }

    @PostMapping("/lanche/criar-lanche")
    public ResponseEntity<Lanche> criarLanche(@RequestBody Lanche lanche){
        Lanche novoLanche = lanchoneteService.calcularPedido(lanche);
        lanchoneteRepository.save(lanche);
        return ResponseEntity.ok(novoLanche);
    }

    public Lanche getLanche(@PathVariable("id") String  id){
        final Optional<Lanche> lanche =lanchoneteRepository.findById(id);
        if(lanche.isPresent()){
            return lanche.get();
        }else {
            throw new LancheNotFoundException();
        }
    }

    @GetMapping("/pedido/{tipo}")
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
