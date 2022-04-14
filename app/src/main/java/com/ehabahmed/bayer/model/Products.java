package com.ehabahmed.bayer.model;

import java.util.ArrayList;

public class Products {

    String code;
    String status;
    String message;
    ArrayList<Product> data;

    public ArrayList<Product> getData() {
        return data;
    }

    public void setData(ArrayList<Product> data) {
        this.data = data;
    }
}
