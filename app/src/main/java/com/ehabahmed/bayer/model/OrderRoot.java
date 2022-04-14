package com.ehabahmed.bayer.model;

import java.util.List;

class OrderItem{
    public int id;
    public int request_id;
    public int product_id;
    public int quantity;
    public String price;
    public String created_at;
    public String updated_at;
    public String general_price;

}


public class OrderRoot{
    public int code;
    public String status;
    public String message;
    public List<OrderData> data;
}

