package com.coronavirus.trackerapp.services;

import com.coronavirus.trackerapp.controllers.DataRepository;
import com.coronavirus.trackerapp.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusFetchData {

    @Autowired
    private DataRepository dataRepository;

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        for (LocationStats loc : dataRepository.findAll()) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(loc.getState());
            locationStats.setCountry(loc.getCountry());
            locationStats.setLatestTotalCases(loc.getLatestTotalCases());
            locationStats.setDiffFromPrevDay(loc.getDiffFromPrevDay());
            newStats.add(locationStats);
        }
        this.allStats = newStats;

    }

}
