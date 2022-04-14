package com.ehabahmed.bayer.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Product  implements Serializable {

    String id;
    String name;
    String price;
    String description;
    String confirmed;
    String category_id;
    String stock;
    String unit;
    String SKU;
    String barcode;
    String product_tax;
    String image;
    String added_by;
    String edited_by;
    String created_at;
    String updated_at;
    String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setProduct_tax(String product_tax) {
        this.product_tax = product_tax;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public void setEdited_by(String edited_by) {
        this.edited_by = edited_by;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getStock() {
        return stock;
    }

    public String getUnit() {
        return unit;
    }

    public String getSKU() {
        return SKU;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProduct_tax() {
        return product_tax;
    }

    public String getImage() {
        return image;
    }

    public String getAdded_by() {
        return added_by;
    }

    public String getEdited_by() {
        return edited_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
