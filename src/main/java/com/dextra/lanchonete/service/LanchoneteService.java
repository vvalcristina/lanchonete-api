package com.dextra.lanchonete.service;

import com.dextra.lanchonete.exception.LancheNotFoundException;
import com.dextra.lanchonete.model.Adicional;
import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.enums.TipoIngrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.repository.LanchoneteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanchoneteService {

    @Autowired
    LanchoneteRepository lanchoneteRepository;

    PromocaoService promocaoService;

    public List<Lanche> findAll(){
        return this.lanchoneteRepository.findAll();
    }

    public Lanche save(Lanche lanche){
        return lanchoneteRepository.save(lanche);
    }

    public void delete(String id) {
        final Lanche lanche = findById(id);
        lanchoneteRepository.delete(lanche);
    }

    public Lanche findById(final String id) {
        final Optional<Lanche> lanche =lanchoneteRepository.findById(id);
        if(lanche.isPresent()){
            return lanche.get();
        }else {
            throw new LancheNotFoundException();
        }

    }
    //Calculando preço do lanche
    public Lanche precoLanche(TipoLanche tipoLanche){
        Lanche lanche = new Lanche();

        List<TipoIngrediente> ingredientes = new ArrayList<>();

        ingredientes.addAll(Lanche.ingredientes(tipoLanche));

        lanche.setPrecoLanche(totalPedido(ingredientes, false));

        return lanche;
    }
    //Calcula o valor total do pedido
    public BigDecimal totalPedido(List<TipoIngrediente> ingredientes, boolean adicional){
        BigDecimal totalPedido = BigDecimal.ZERO;

        for (TipoIngrediente tipoIngrediente: ingredientes){
            totalPedido = totalPedido.add(precoIngrediente(tipoIngrediente));
        }
        if(adicional){
            totalPedido = promocaoService.promocaoCarneQueijo(ingredientes, totalPedido);

            totalPedido = promocaoService.promocaoLight(ingredientes, totalPedido);
        }
        return totalPedido;
    }

    //Preparando o pedido
    public Lanche calcularPedido(Lanche lanche){
        Lanche pedido = new Lanche();
        boolean adicional = false;

        if(pedido == null){
            return pedido;
        }
        List<TipoIngrediente>ingredienteLanche = new ArrayList<>();

        if(pedido.getTipoLanche() != null){
            ingredienteLanche.addAll(Lanche.ingredientes(pedido.getTipoLanche()));
            if (pedido.getAdicionais() != null && !pedido.getAdicionais().isEmpty()){
                adicional = true;
                for (Adicional extra: pedido.getAdicionais()){
                    for(int i=0; i<extra.getQuantidade(); i++){
                        ingredienteLanche.add(extra.getTipoIngrediente());
                    }
                }
            }
            pedido.setPrecoLanche(totalPedido(ingredienteLanche, adicional));
        }
        return pedido;
    }
    //Preço do ingrediente
    public BigDecimal precoIngrediente(TipoIngrediente tipoIngrediente){
        switch (tipoIngrediente){
            case ALFACE:
                return new BigDecimal(0.40);
            case BACON:
                return new BigDecimal(2.00);
            case HAMBURGUER:
                return new BigDecimal(3.00);
            case OVO:
                return new BigDecimal(0.80);
            case QUEIJO:
                return new BigDecimal(1.50);
        }
        return BigDecimal.ZERO;
    }

}
