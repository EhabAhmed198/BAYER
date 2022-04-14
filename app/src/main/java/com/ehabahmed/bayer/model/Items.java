package com.ehabahmed.bayer.model;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("product_id")
    String product_id;
    @SerializedName("qty")
    String qty;

    public Items(String product_id, String qty) {
        this.product_id = product_id;
        this.qty = qty;
    }

    public Items() {
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}