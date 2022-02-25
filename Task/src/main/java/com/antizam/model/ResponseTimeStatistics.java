package com.antizam.model;

/*************************************************
         Model za objekt
         {
             "url":"https://simple-scala-api.herokuapp.com/api1",
             "average_response_time":4041,
         }

 *********************************************/

public class ResponseTimeStatistics {
    private String url;
    private double average_response_time;

    public ResponseTimeStatistics() {
    }

    public ResponseTimeStatistics(String url, double average_response_time) {
        this.url = url;
        this.average_response_time = average_response_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getAverage_response_time() {
        return average_response_time;
    }

    public void setAverage_response_time(double average_response_time) {
        this.average_response_time = average_response_time;
    }

    @Override
    public String toString() {
        return "ResponseTimeStatistics{" +
                "url='" + url + '\'' +
                ", average_response_time=" + average_response_time +
                '}';
    }
}
