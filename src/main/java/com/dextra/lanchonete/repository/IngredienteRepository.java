package com.dextra.lanchonete.repository;

import com.dextra.lanchonete.model.Ingrediente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends MongoRepository<Ingrediente, String> {

   List<Ingrediente>findAll();
}
