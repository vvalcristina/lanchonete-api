package com.dextra.lanchonete.repository;

import com.dextra.lanchonete.model.Lanche;
import com.dextra.lanchonete.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanchoneteRepository extends MongoRepository<Pedido, String> {

    List<Pedido> findAll();

}
