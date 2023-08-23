package org.kainos.ea.core;

import org.kainos.ea.cli.ProductRequest;

public class ProductValidator {
    public String isValidProduct(ProductRequest product){
        if(product.getName().length() > 50){
            return "Name Greater than 50 Characters";
        }

        if(product.getDescription().length() > 500){
            return "Description Greater than 500 Characters";
        }

        if(product.getPrice() < 10){
            return "Price less than £10";
        }

        return null;
    }
}
