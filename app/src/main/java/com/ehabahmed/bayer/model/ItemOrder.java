package com.ehabahmed.bayer.model;

import java.io.Serializable;

public class ItemOrder implements Serializable {
    public int id;
    public int request_id;
    public int product_id;
    public int quantity;
    public String price;
    public String created_at;
    public String updated_at;
    public String general_price;
    public String product_name;
}