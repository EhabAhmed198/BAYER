package com.ehabahmed.bayer.model;

import java.util.ArrayList;

public class Pharmacy {

    String code;
    String status;
    String message;
    ArrayList<PharmacyBranches> data;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<PharmacyBranches> getData() {
        return data;
    }

    public void setData(ArrayList<PharmacyBranches> data) {
        this.data = data;
    }
}
