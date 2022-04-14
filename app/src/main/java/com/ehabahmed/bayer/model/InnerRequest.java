package com.ehabahmed.bayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InnerRequest {

    @SerializedName("status")
    String status;
    @SerializedName("grand_total")
    String grand_total;
    @SerializedName("net_total")
    String net_total;
    @SerializedName("tax_amount")
    String tax_amount;
    @SerializedName("item_count")
    String item_count;
    @SerializedName("payment_status")
    String payment_status;
    @SerializedName("payment_method")
    String payment_method;
    @SerializedName("name")
    String name;
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("location")
    String location;
    @SerializedName("added_by")
    String added_by;
    @SerializedName("pharmacy_id")
    String pharmacy_id;
    @SerializedName("distributor_id")
    String distributor_id;
    @SerializedName("updated_at")
    String updated_at;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("id")
    String id;
    @SerializedName("items")
    ArrayList<RequestItem> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getNet_total() {
        return net_total;
    }

    public void setNet_total(String net_total) {
        this.net_total = net_total;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<RequestItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<RequestItem> items) {
        this.items = items;
    }
}
