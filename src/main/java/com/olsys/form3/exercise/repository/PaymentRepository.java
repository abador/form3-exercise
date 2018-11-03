package com.olsys.form3.exercise.repository;

import com.olsys.form3.exercise.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "payment", path = "payment")
public interface PaymentRepository extends MongoRepository<Payment,UUID> {
}
