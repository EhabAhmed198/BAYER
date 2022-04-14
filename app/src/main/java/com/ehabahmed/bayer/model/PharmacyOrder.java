package com.ehabahmed.bayer.model;

import java.util.ArrayList;

public class PharmacyOrder {


    String code;
    String status;
    String message;
    PharmacyBranches data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PharmacyBranches getData() {
        return data;
    }

    public void setData(PharmacyBranches data) {
        this.data = data;
    }
}
