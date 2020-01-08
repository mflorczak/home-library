package com.pk.home.library.library.controller;

import com.pk.home.library.library.enumtype.StatisticModes;
import com.pk.home.library.library.model.DifferentStatistic;
import com.pk.home.library.library.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/statistics")
@AllArgsConstructor
public class StatisticController {

    private StatisticService statisticService;

    @GetMapping("/all-book")
    public ResponseEntity<DifferentStatistic> getAllBookQuantity(@RequestParam("mode") StatisticModes statisticModes) {
        return ResponseEntity.ok(statisticService.getStatistic(statisticModes).calculateStatistic());
    }
}
