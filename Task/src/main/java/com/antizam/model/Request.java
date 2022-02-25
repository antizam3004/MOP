package com.antizam.model;

import javax.persistence.*;
import java.util.Date;

/*************************************************
         Model za objekt
         {
             "request_id":0,
             "url":"https://simple-scala-api.herokuapp.com/api1",
             "request_executed_at":"2021-11-16",
             "response_time:"4321"
         }

 *********************************************/

@Entity
@Table(name = "requests_tbl")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;

    private String url;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date request_executed_at;
    private int response_time;

    public Request(){}

    public Request(String url, Date request_executed_at, int response_time) {
        this.url = url;
        this.request_executed_at = request_executed_at;
        this.response_time = response_time;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRequest_executed_at() {
        return request_executed_at;
    }

    public void setRequest_executed_at(Date request_executed_at) {
        this.request_executed_at = request_executed_at;
    }

    public int getResponse_time() {
        return response_time;
    }

    public void setResponse_time(int response_time) {
        this.response_time = response_time;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request_id=" + request_id +
                ", url='" + url + '\'' +
                ", request_executed_at=" + request_executed_at +
                ", response_time=" + response_time +
                '}';
    }
}
