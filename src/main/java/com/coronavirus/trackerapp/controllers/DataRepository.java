package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.LocationStats;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DataRepository extends MongoRepository<LocationStats, String> {

    public LocationStats findByCountry(String country);

    public LocationStats findByState(String state);

}
