package com.ehabahmed.bayer.model;

import java.util.List;

public class OrderData{
    public int id;
    public int order_status;
    public int order_type;
    public String status;
    public String grand_total;
    public String net_total;
    public String tax_amount;
    public int item_count;
    public int payment_status;
    public String payment_method;
    public String name;
    public String phone_number;
    public String location;
    public Object notes;
    public Object delivery_time;
    public Object delivery_boy_id;
    public int added_by;
    public int pharmacy_id;
    public int parent_id;
    public String created_at;
    public String updated_at;
    public int distributor_id;
    public List<OrderItem> items;
}
