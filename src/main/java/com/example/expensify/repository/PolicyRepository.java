package com.example.expensify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensify.model.Policy;

public interface PolicyRepository extends MongoRepository<Policy, String> {

}
