package com.pk.home.library.library.controller;

import com.pk.home.library.library.enumtype.StatisticModes;
import com.pk.home.library.library.model.DifferentStatistic;
import com.pk.home.library.library.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticController {

    private StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/all-book")
    public ResponseEntity<DifferentStatistic> getAllBookQuantity(@RequestParam("mode") StatisticModes statisticModes) {
        return ResponseEntity.ok(statisticService.getStatistic(statisticModes).calculateStatistic());
    }
}
