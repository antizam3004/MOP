package com.antizam.service;

import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    String url;

    public MyCallable(String url){
        this.url=url;
    }

    @Override
    public String call() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result= restTemplate.getForObject(url, String.class);
        return result;
    }
}
