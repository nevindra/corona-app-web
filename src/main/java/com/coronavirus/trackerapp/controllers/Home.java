package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.LocationStats;
import com.coronavirus.trackerapp.services.CoronaVirusDataService;
import com.coronavirus.trackerapp.services.CoronaVirusFetchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {

    @Autowired
    CoronaVirusFetchData coronaVirusFetchData;

    @Autowired
    private DataRepository dataRepository;

    @GetMapping("/")
    public String home(@ModelAttribute LocationStats locationStats, Model model) {

        int totalReportedCases = 0;
        for (LocationStats loc : dataRepository.findAll()) {
            totalReportedCases += loc.getLatestTotalCases();
        }
        List<LocationStats> allStat = coronaVirusFetchData.getAllStats();
        float progress = (float) 100000000 / totalReportedCases;
        model.addAttribute("datas", allStat );
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("progress", progress);
        return "home";
    }
}
