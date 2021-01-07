package com.coronavirus.trackerapp.controllers;

import com.coronavirus.trackerapp.models.LocationStats;
import com.coronavirus.trackerapp.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCase = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int progress = 100000000 / totalCase;
        model.addAttribute("data", allStats );
        model.addAttribute("totalReportedCases", totalCase);
        model.addAttribute("progress", progress);
        return "home";
    }
}
