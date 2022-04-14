package com.ehabahmed.bayer.model;

import com.google.gson.annotations.SerializedName;

public class RequestItem {
@SerializedName("id")
    String id;
    @SerializedName("request_id")
    String request_id;
    @SerializedName("product_id")
    String product_id;
    @SerializedName("quantity")
    String quantity;
    @SerializedName("price")
    String price;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;
    @SerializedName("general_price")
    String general_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getGeneral_price() {
        return general_price;
    }

    public void setGeneral_price(String general_price) {
        this.general_price = general_price;
    }
}
