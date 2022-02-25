package com.antizam.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/*************************************************
        Model za objekt
             {
             "product_id":0,
             "prices":[621.83796496454, 455.5564564554]
             }

 ****************************************************/

public class ProductPrices {

    private int product_id;
    private ArrayList<BigDecimal> prices=new ArrayList<>();

    public ProductPrices() {
    }
    public ProductPrices(int product_id, BigDecimal prices){
        this.product_id=product_id;
        this.prices.add(prices);
    }
    public ProductPrices(int product_id, ArrayList<BigDecimal> prices){
        this.product_id=product_id;
        this.prices=prices;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public ArrayList<BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<BigDecimal> prices) {
        this.prices = prices;
    }
}
