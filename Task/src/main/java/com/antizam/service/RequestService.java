package com.antizam.service;

import com.antizam.repository.RequestRepository;
import com.antizam.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveAllRequest(List<Request> requests){
        requestRepository.saveAll(requests);

    }
}
