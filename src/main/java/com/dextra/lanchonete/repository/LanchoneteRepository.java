package com.dextra.lanchonete.repository;

import com.dextra.lanchonete.model.Lanche;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanchoneteRepository extends MongoRepository<Lanche, String> {

    List<Lanche> findAll();

}
