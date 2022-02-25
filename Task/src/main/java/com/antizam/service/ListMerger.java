package com.antizam.service;

import com.antizam.model.Product;
import com.antizam.model.ProductPrices;

import java.math.BigDecimal;
import java.util.*;

public class ListMerger {

    /* Klasa u koju šaljemo gotove requestove na "spajanje"*/

    TreeMap<Integer, ArrayList<BigDecimal>> resultSet=new TreeMap<>();

    public ListMerger(){
    }

    public ArrayList<ProductPrices> merge(List<Product> products){

        //ubacujemo u TreeMap
        for(Product product:products){
            if(resultSet.containsKey(product.getProduct_id())){
                resultSet.get(product.getProduct_id()).add(product.getPrice());
            }
            else{
                resultSet.put(product.getProduct_id(), new ArrayList<>(Arrays.asList(product.getPrice())));
            }

        }
        //treemap dijelimo u dvije liste da bi ih spojili u ArrayList kao rezultat
        ArrayList<Integer> keys=new ArrayList<>(resultSet.keySet());
        ArrayList<ArrayList<BigDecimal>> values=new ArrayList<ArrayList<BigDecimal>>(resultSet.values());
        ArrayList<ProductPrices> result=new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {
            result.add(new ProductPrices(keys.get(i),  values.get(i)));
        }
        return result;
    }

}
