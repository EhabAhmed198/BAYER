package com.ehabahmed.bayer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {


    String order_type;
   String name;
    String phone_number;
    String location;
    @SerializedName("note")
    String note;
    String pharmacy_id;
    String distributor_id;
    String pharmacy_branch_id;

    public Order() {
    }

    List<Items> items;

    public Order(String order_type, String name, String phone_number, String location, String note, String pharmacy_id, String distributor_id, String pharmacy_branch_id, List<Items> items) {
        this.order_type = order_type;
        this.name = name;
        this.phone_number = phone_number;
        this.location = location;
        this.note = note;
        this.pharmacy_id = pharmacy_id;
        this.distributor_id = distributor_id;
        this.pharmacy_branch_id = pharmacy_branch_id;
        this.items = items;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getPharmacy_branch_id() {
        return pharmacy_branch_id;
    }

    public void setPharmacy_branch_id(String pharmacy_branch_id) {
        this.pharmacy_branch_id = pharmacy_branch_id;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
