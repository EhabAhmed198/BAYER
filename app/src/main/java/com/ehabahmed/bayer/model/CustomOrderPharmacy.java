package com.ehabahmed.bayer.model;

public class CustomOrderPharmacy {

    OrderData order;
    PharmacyOrder pharmacy;

    public CustomOrderPharmacy(OrderData order, PharmacyOrder pharmacy) {
        this.order = order;
        this.pharmacy = pharmacy;
    }

    public OrderData getOrder() {
        return order;
    }

    public void setOrder(OrderData order) {
        this.order = order;
    }

    public PharmacyOrder getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyOrder pharmacy) {
        this.pharmacy = pharmacy;
    }
}
