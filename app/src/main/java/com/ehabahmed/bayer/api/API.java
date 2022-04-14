package com.ehabahmed.bayer.api;


import com.ehabahmed.bayer.model.Order;

import com.ehabahmed.bayer.model.OrderRootCompletModel;
import com.ehabahmed.bayer.model.OuterRequest;
import com.ehabahmed.bayer.model.Pharmacy;
import com.ehabahmed.bayer.model.PharmacyOrder;
import com.ehabahmed.bayer.model.Products;
import com.ehabahmed.bayer.model.Root;

import com.ehabahmed.bayer.model.Token;




import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface API {


    @POST("api/login")
    @Multipart
    Call<Token>
    setUserDataInOITaswol(@Part("email") RequestBody email, @Part("password") RequestBody password);

    @GET("api/user")
    Call<Root>getDataUser(@Header("Authorization") String Authorization);


    @GET("api/user/pharmacies")
    Call<Pharmacy>getPharmacy(@Header("Authorization") String Authorization);



    @GET("api/products")
    Call<Products>getProducts(@Header("Authorization") String Authorization);


  //https://buyer.kantoegypt.com/api/pharmacy/3

    @GET
    Call<PharmacyOrder>getPharmacyOrder(@Header("Authorization") String Authorization, @Url String Url);






//    @POST("api/user/add_request")
//   @FormUrlEncoded
//    Call<OuterRequest>
//    sendData(@Header("Authorization") String Authorization,
//             @Field("order_type") String order_type,
//             @Field("name") String name
//           , @Field("phone_number") String phone_number,
//             @Field("location") String location
//            , @Field("note") String note,
//             @Field("pharmacy_id") String pharmacy_id
//            , @Field("distributor_id") String distributor_id,
//             @Field("pharmacy_branch_id") String pharmacy_branch_id
//           , @Field("items[]") List<Items> items);


    @POST("api/user/add_request")
    Call<OuterRequest>
    sendData(@Header("Authorization") String Authorization,
             @Body Order order);

//    @GET("api/user/requests")
//    Call<OrderRoot>getOrders(@Header("Authorization") String Authorization);
//https://buyer.kantoegypt.com/api/user/requests
    @GET("api/user/requests")
    Call<OrderRootCompletModel>getOrders(@Header("Authorization") String Authorization);



}


