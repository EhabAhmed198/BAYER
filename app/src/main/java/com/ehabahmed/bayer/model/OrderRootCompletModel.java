package com.ehabahmed.bayer.model;

import java.io.Serializable;
import java.util.List;



public class OrderRootCompletModel implements Serializable {
    public int code;
    public String status;
    public String message;
    public List<Datum> data;



}


