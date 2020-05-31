package com.dextra.lanchonete.service;

import com.dextra.lanchonete.exception.PedidosNotFoundException;
import com.dextra.lanchonete.model.Adicional;
import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.Pedido;
import com.dextra.lanchonete.model.enums.Ingrediente;
import com.dextra.lanchonete.model.enums.TipoLanche;
import com.dextra.lanchonete.repository.LanchoneteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    LanchoneteRepository lanchoneteRepository;

    PromocaoService promocaoService;

    public List<Pedido> findAll(){
        return this.lanchoneteRepository.findAll();
    }

    public Pedido save(Pedido pedido){
        calcularPedido(pedido);
        return lanchoneteRepository.save(pedido);
    }

    public void delete(String id) {
        final Pedido lanche = findById(id);
        lanchoneteRepository.delete(lanche);
    }

    public Pedido findById(final String id) {
        final Optional<Pedido> lanche =lanchoneteRepository.findById(id);
        if(lanche.isPresent()){
            return lanche.get();
        }else {
            throw new PedidosNotFoundException();
        }

    }
    //Calculando preço do lanche
    public Lanche precoLanche(TipoLanche tipoLanche){
        Lanche lanche = new Lanche();

        List<Ingrediente> ingredientes = new ArrayList<>();

        ingredientes.addAll(Lanche.ingredientes(tipoLanche));

        lanche.setPreco(formatValor(totalPedido(ingredientes, false)));

        return lanche;
    }
    //Calcula o valor total do pedido
    public BigDecimal totalPedido(List<Ingrediente> ingredientes, boolean adicional){
        BigDecimal totalPedido = BigDecimal.ZERO;

        for (Ingrediente tipoIngrediente: ingredientes){
            totalPedido = totalPedido.add(this.precoIngrediente(tipoIngrediente));
        }
        if(adicional){
            totalPedido = promocaoService.promocaoCarneQueijo(ingredientes, totalPedido);

            totalPedido = promocaoService.promocaoLight(ingredientes, totalPedido);
        }
        return totalPedido;
    }

    //Preparando o pedido
    public Pedido calcularPedido(Pedido pedidos){
        Pedido pedido = new Pedido();
        boolean adicional = false;

        if(pedido == null){
            return pedido;
        }
        List<Ingrediente>ingredienteLanche = new ArrayList<>();

        if(pedido.getLanche() != null){
            ingredienteLanche.addAll(Lanche.ingredientes(pedido.getLanche()));
            if (pedido.getAdicionais() != null && !pedido.getAdicionais().isEmpty()){
                adicional = true;
                for (Adicional extra: pedido.getAdicionais()){
                    for(int i=0; i<extra.getQuantidade(); i++){
                        ingredienteLanche.add(extra.getIngrediente());
                    }
                }
            }
            pedido.setTotal(formatValor(totalPedido(ingredienteLanche, adicional)));
        }
        return pedido;
    }
    //Preço do ingrediente
    public BigDecimal precoIngrediente(Ingrediente tipoIngrediente){
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

    private String formatValor(BigDecimal valor) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMinimumFractionDigits(2);
        return decimalFormat.format(valor);
    }


}
