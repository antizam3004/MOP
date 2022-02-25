package com.antizam.api;

import com.antizam.service.ResponseTimeStatisticsService;
import com.antizam.model.ResponseTimeStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/responseTimeStatistics")
public class ResponseTimeStatisticsController {

    private final ResponseTimeStatisticsService responseTimeStatisticsService;

    @Autowired
    public ResponseTimeStatisticsController(ResponseTimeStatisticsService responseTimeStatisticsService) {
        this.responseTimeStatisticsService = responseTimeStatisticsService;
    }

    @GetMapping
    public List<ResponseTimeStatistics> getResponseTimeStatistics(){
        return responseTimeStatisticsService.getData();
    }
}
