package com.ehabahmed.bayer

import android.Manifest
import android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehabahmed.bayer.adapter.SelectedProductAdapter
import com.ehabahmed.bayer.api.API
import com.ehabahmed.bayer.model.*
import com.ehabahmed.bayer.prevalent.Prevalent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.paperdb.Paper
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import java.lang.Exception


class ConfirmActivity : AppCompatActivity() {
    lateinit var listItems:ArrayList<Product>;


    lateinit var pharmacyName:String;
    lateinit var pharmacyId:String;
    lateinit var sendOrderButton: Button;
    var price:Float = 0.0f;
    lateinit var selectProductRecyclerView:RecyclerView;
    lateinit var adapter:SelectedProductAdapter;
    lateinit var retrofit: Retrofit;
    lateinit var api: API;
//    lateinit var checkOrderdBox:CheckBox;
     var order_type:String="0";
    lateinit var items:ArrayList<Items>;
    lateinit  var noteEditText: EditText;
     var note="";
    lateinit var location:String;
    lateinit var pharmacyTitle: TextView;
    lateinit var floatingActionButton: FloatingActionButton;
    lateinit var radioGroup:RadioGroup;
    lateinit var locationManager: LocationManager
    var currentLongitude: Double = 0.0
    var currentLatitude: Double = 0.0
    lateinit var dialog: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        dialog = AlertDialog.Builder(this@ConfirmActivity);
        checkLocationService()

        sendOrderButton=findViewById(R.id.sendOrder);
        sendOrderButton.isEnabled=false;
//        checkOrderdBox=findViewById(R.id.orderd);
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
           if(i==R.id.selectedButton){
              order_type="1";


           } else if(i==R.id.notselectedButton){

               order_type="0";

           }
        }
        noteEditText=findViewById(R.id.noteEditText);
        pharmacyTitle=findViewById(R.id.pharmacy_title);

        items= ArrayList();
//        checkOrderdBox.setOnCheckedChangeListener { compoundButton, b ->
//            if(b==true)
//                order_type="1";
//            else
//                order_type="0";
//        }
        listItems = intent.getSerializableExtra("orderList")as ArrayList<Product>;
        pharmacyName=intent.getStringExtra("pharmacyName").toString();
        pharmacyId=intent.getStringExtra("pharmacyId").toString();
        location=intent.getStringExtra("pharmacylocation").toString();


        pharmacyTitle.setText(pharmacyName);
        Paper.init(this);
        selectProductRecyclerView=findViewById(R.id.selected_order);
        selectProductRecyclerView.layoutManager=LinearLayoutManager(this@ConfirmActivity);
        for(item in listItems){
         var pItem:Float=item.price.toFloat()*item.quantity.toFloat();
            price+=pItem;

            items.add(

                Items(
                    (item.id.toString()),
                    (item.quantity.toString())
                )
            );

        }

    adapter=SelectedProductAdapter(this@ConfirmActivity,listItems);
        selectProductRecyclerView.adapter=adapter;
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        api=retrofit.create(API::class.java)
        sendOrderButton.setOnClickListener {
      var phone_number=Paper.book(Prevalent.USER_BOOK).read<String>(Prevalent.USER_phone,"");
//            val rOrderType = RequestBody.create(MediaType.parse("multipart/form-data"), "1")
//            val rname = RequestBody.create(MediaType.parse("multipart/form-data"), "")
//            val rPhoneNumber = RequestBody.create(MediaType.parse("multipart/form-data"), "201002325145")
//            val location = RequestBody.create(MediaType.parse("multipart/form-data"), "12.654654,22.56465")
//            val note = RequestBody.create(MediaType.parse("multipart/form-data"), "sdfsdfsadfasdfgasdgsadg")
//            val rPharmacyId = RequestBody.create(MediaType.parse("multipart/form-data"), "3")
//            val rDistributorId = RequestBody.create(MediaType.parse("multipart/form-data"), "1")
//            val rPharmacyBranchId = RequestBody.create(MediaType.parse("multipart/form-data"), "4")

            var token=Paper.book(Prevalent.USER_BOOK).read<String>(Prevalent.USER_TOKEN);
            note=noteEditText.text.trim().toString();
            var loca=currentLatitude.toString()+","+currentLongitude.toString();

        api.sendData(token,Order(order_type,"developer4",phone_number,loca,
        note,pharmacyId,"","",items)).enqueue(object:Callback<OuterRequest>{
            override fun onResponse(call: Call<OuterRequest>, response: Response<OuterRequest>) {
                if(response.isSuccessful){
                    Toast.makeText(this@ConfirmActivity,"order added Scuccessfully...",Toast.LENGTH_LONG).show()
                    var startHome=Intent(this@ConfirmActivity,UserHomeActivity::class.java);
                   startHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(startHome);
                    finish()
                }
            }

            override fun onFailure(call: Call<OuterRequest>, t: Throwable) {
                Toast.makeText(this@ConfirmActivity,"no internet connection...", Toast.LENGTH_LONG).show()

            }
        })

}

        floatingActionButton=findViewById(R.id.add);
        floatingActionButton.setOnClickListener {
            var startHomeUser=Intent(this@ConfirmActivity,UserHomeActivity::class.java);
            startHomeUser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(startHomeUser)
        }

//      var status=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        if(status) {
//
//
//        } else {
//            checkPermissionSAndroidHigh2()
//
//        }

    }
    private fun checkLocationService() {

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            dialog.setTitle("GPS Disabled")
            dialog.setMessage("Your GPS seems to be disabled, must enable GPS to continue..")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, which ->

                checkLocationService()

            })
            dialog.show()
        } else {

           if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
               checkPermissionSAndroidHigh1()
           }
         else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermissionApp()
            }
        }
    }

    private fun checkPermissionSAndroidHigh1() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, true) -> {
                        checkPermissionApp()
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, true) -> {
                        checkPermissionApp()
                    } else -> {
                    // No location access granted.
                }
                }
            }
        }

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    private fun checkPermissionSAndroidHigh2() {
        val locationPermissionRequest2 = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        getCurrentLocationForMobilePhone()
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        getCurrentLocationForMobilePhone()
                    } else -> {
                    // No location access granted.
                }
                }
            }
        }

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
        locationPermissionRequest2.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermissionApp() {

       if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
           requestPermissions(
               arrayOf(
                   ACCESS_FINE_LOCATION,    ACCESS_BACKGROUND_LOCATION
               ), 2
           );
       }else {

               getCurrentLocationForMobilePhone()

       }
    }

    fun getCurrentLocationForMobilePhone() {


        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                     arrayOf(
                        ACCESS_FINE_LOCATION, ACCESS_BACKGROUND_LOCATION
                    ), 2

                )
            }

        }
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            locationListener

        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 2 && PERMISSION_GRANTED in grantResults) {
            getCurrentLocationForMobilePhone()
        }
    }


        private var locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                currentLongitude = p0!!.longitude
                currentLatitude = p0!!.latitude
                sendOrderButton.isEnabled=true;

            }

        }
}