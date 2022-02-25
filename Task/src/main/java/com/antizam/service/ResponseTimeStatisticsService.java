package com.antizam.service;

import com.antizam.repository.RequestRepository;
import com.antizam.model.Request;
import com.antizam.model.ResponseTimeStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseTimeStatisticsService {
    private final RequestRepository requestRepository;

    @Autowired
    public ResponseTimeStatisticsService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<ResponseTimeStatistics> getData(){
        //dohvati sve
        List<Request> allRequests =requestRepository.findAll();

        //rezultat
        List<ResponseTimeStatistics> result=new ArrayList<>();

        for (int i = 0; i < allRequests.size(); i++) {
            result.add(new ResponseTimeStatistics(allRequests.get(i).getUrl(), requestRepository.getAverage(allRequests.get(i).getRequest_executed_at())));
        }
        return result;
    }
}
