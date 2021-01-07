package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository <CustomerDTO, String> {

    public CustomerDTO findByUsername(String username);

    public List<CustomerDTO> findByPassword(String password);

}
