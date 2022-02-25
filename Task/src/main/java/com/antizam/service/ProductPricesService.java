package com.antizam.service;

import com.antizam.model.ProductPrices;
import com.antizam.model.Product;
import com.antizam.model.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProductPricesService {
    final ObjectMapper objectMapper=new ObjectMapper();

    ListenableFuture<String> asyncTask, asyncTask2, asyncTask3, asyncTask4;

    private final RequestService requestService;

    //4 liste koje popunjavamo iz svakog requesta
    ArrayList<Product> productList1=new ArrayList<>();
    ArrayList<Product> productList2=new ArrayList<>();
    ArrayList<Product> productList3=new ArrayList<>();
    ArrayList<Product> productList4=new ArrayList<>();

    ArrayList[] productLists={productList1, productList2, productList3, productList4};

    String[] urls={ "https://simple-scala-api.herokuapp.com/api1",
                    "https://simple-scala-api.herokuapp.com/api2",
                    "https://simple-scala-api.herokuapp.com/api3",
                    "https://simple-scala-api.herokuapp.com/api4"};

    //rezultat koji vraćamo na get poziv
    ArrayList<ProductPrices> resultList;

    //rezultat iz Task2(bonus) koji šaljemo prema bazi
    ArrayList<Request> requests=new ArrayList<>();

    ListenableFuture[] futures=new ListenableFuture[4];

    ExecutorService executor= Executors.newFixedThreadPool(4);
    ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(executor);

    @Autowired
    public ProductPricesService(RequestService requestService) {
        this.requestService = requestService;
    }

    public ArrayList<ProductPrices> getData() throws ExecutionException, InterruptedException, JsonProcessingException {

        requestCounter=0; //završenih requestova je 0
        requests=new ArrayList<>();

        asyncTask=(ListenableFuture<String>) ((ExecutorService) listeningExecutor).submit(new MyCallable(urls[0]));
        asyncTask2=(ListenableFuture<String>) ((ExecutorService) listeningExecutor).submit(new MyCallable(urls[1]));
        asyncTask3=(ListenableFuture<String>) ((ExecutorService) listeningExecutor).submit(new MyCallable(urls[2]));
        asyncTask4=(ListenableFuture<String>) ((ExecutorService) listeningExecutor).submit(new MyCallable(urls[3]));

        futures[0]=asyncTask;
        futures[1]=asyncTask2;
        futures[2]=asyncTask3;
        futures[3]=asyncTask4;

        startAsyncTask(asyncTask);
        startAsyncTask(asyncTask2);
        startAsyncTask(asyncTask3);
        startAsyncTask(asyncTask4);

        productList1=objectMapper.readValue(asyncTask.get(), new TypeReference<ArrayList<Product>>(){});
        productList2=objectMapper.readValue(asyncTask.get(), new TypeReference<ArrayList<Product>>(){});
        productList3=objectMapper.readValue(asyncTask.get(), new TypeReference<ArrayList<Product>>(){});
        productList4=objectMapper.readValue(asyncTask.get(), new TypeReference<ArrayList<Product>>(){});

        ListMerger listMerger = new ListMerger();
        resultList = listMerger.merge(productList1);
        resultList = listMerger.merge(productList2);
        resultList = listMerger.merge(productList3);
        resultList = listMerger.merge(productList4);

        return resultList;

    }

    private void startAsyncTask(ListenableFuture<String> asyncTask){
        Date requestExecutedAt=new Date(System.currentTimeMillis());
        long start=System.currentTimeMillis();  //početak

        Futures.addCallback(asyncTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                long responseTime=System.currentTimeMillis()-start;
                traceRequests(3, requestExecutedAt, (int) responseTime);
            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, listeningExecutor);
    }


    //brojač završenih requestova
    int requestCounter=0;


    //polje booleana preko kojeg ćemo vidjeti koji su requestovi završili kao prva 3
    boolean[] finishedArray=new boolean[4];


    //***metoda koja prekida 4. request i šalje tri liste na ResponseTimeStatisticsServis čim requestcounter dođe do 3
    private void traceRequests(int futureNumber, Date requestExecutedAt, int responseTime)
    {
        finishedArray[futureNumber]=true;
        requestCounter++;
        requests.add(new Request(urls[futureNumber], requestExecutedAt, responseTime));
        if(requestCounter==3){  //tri su gotove i sad šaljemo u bazu
            for(int i=0;i<finishedArray.length;i++){

                if(finishedArray[i]==false){//zadnja koja je ostala
                    productLists[i]=new ArrayList();    //čistimo listu da neide u ListMerger.merge()
                    futures[i].cancel(true);
                }
                else{

                }
            }
            requestService.saveAllRequest(requests);
        }
    }





}
