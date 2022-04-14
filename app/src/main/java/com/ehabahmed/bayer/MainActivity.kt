package com.ehabahmed.bayer

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


import com.ehabahmed.bayer.api.API
import com.ehabahmed.bayer.model.*
import com.ehabahmed.bayer.prevalent.Prevalent
import io.paperdb.Paper
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var userNameEditText:EditText;
    lateinit var passwordEditText: EditText;
    lateinit var loginButton: Button;
    lateinit var username:String;
    lateinit var password:String;
    lateinit var builder: AlertDialog.Builder ;
    lateinit var dialog: AlertDialog;
    lateinit var face: Typeface;
    lateinit var retrofit:Retrofit;
    lateinit var api: API;
lateinit var requestQueue: RequestQueue;

lateinit var nToken: String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paper.init(this@MainActivity)

  var check= Paper.book(Prevalent.USER_BOOK).read<String>(Prevalent.USER_TOKEN,null);
        face=Typeface.createFromAsset(assets,"klavika-light.otf");


        userNameEditText=findViewById(R.id.et_username_login);
        passwordEditText=findViewById(R.id.et_password_login);
        loginButton=findViewById(R.id.bt_login);
        builder=AlertDialog.Builder(this@MainActivity);
       requestQueue=Volley.newRequestQueue(this@MainActivity);
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
                if(check!=null && !check.isEmpty()) {
                    setProgressDialog("Login...")
getUserData(check);
        }


        loginButton.setOnClickListener {
           checkDataLogin();
            //login()
        }


    }

    private fun checkDataLogin() {
        setProgressDialog("Login...")
        username = userNameEditText.text.toString().trim();
        password = passwordEditText.text.toString().toString().trim();
        if (username.isEmpty()) {
            userNameEditText.setError("enter username");
            dialog.dismiss()
        } else if (password.isEmpty()) {

            passwordEditText.setError("enter password");
            dialog.dismiss()
        } else {


          login();


        }

    }

    private fun login() {
        api=retrofit.create(API::class.java)
        val rUsername = RequestBody.create(MediaType.parse("multipart/form-data"), username)
        val rPassword = RequestBody.create(MediaType.parse("multipart/form-data"), password)
        api.setUserDataInOITaswol(rUsername,rPassword).enqueue(object : Callback<Token>{
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                var token=response.body()?.token;
                if(token?.isEmpty() == true || token==null) {
                    Toast.makeText(
                        this@MainActivity,
                        "userName or password faild..",
                        Toast.LENGTH_LONG
                    ).show()
                dialog.dismiss()
                } else {
//                    startActivity(Intent(this@MainActivity,UserHomeActivity::class.java))
        getUserData(token)
                    dialog.dismiss()
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Please check your connection to the internet",Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
        })
    }

//    private fun getUserData(token: String) {
//        var url="http://buyer.kantoegypt.com/api/user?Authorization=Bearer "+token.toString();
//        val stringRequest = JsonObjectRequest(  url, null,
//            com.android.volley.Response.Listener {
//
//            Toast.makeText(this@MainActivity,""+it,Toast.LENGTH_LONG).show();
//
//        },com.android.volley.Response.ErrorListener {
//
//                Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_LONG).show();
//            }
//        )
//        requestQueue.add(stringRequest)
//
//    }

//    private fun getUserData(token: String) {
//
//   var url="https://buyer.kantoegypt.com/api/user?Authorization=Bearer "+token.toString();
//    val request= JsonObjectRequest(url,com.android.volley.Response.Listener {
//                            var user= it.get("user");
//        Toast.makeText(this@MainActivity,"email "+user.toString(),Toast.LENGTH_LONG).show()
//
//    },com.android.volley.Response.ErrorListener {
//
//        Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_LONG).show();
//
//    })
//        requestQueue.add(request);
//
//
//
//
//    }

    private fun getUserData(token: String) {
        api=retrofit.create(API::class.java)
        if(!token.startsWith("Bearer"))
        nToken="Bearer ${token.toString()}"
else
    nToken=token;
        api.getDataUser(nToken).enqueue(object:Callback<Root>{
            override fun onResponse(call: Call<Root>, response: Response<Root>) {

if(response.isSuccessful){
    var user: User? =response.body()?.user;

                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_TOKEN,nToken);
                if(user?.id!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_id,user?.id);
                if(user?.name!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_name,user?.name);
                if(user?.email!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_email,user?.email);
                if(user?.email_verified_at!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_email_verified_at,user?.email_verified_at);
                if(user?.created_at!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_created_at,user?.created_at);
                if(user?.updated_at!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_updated_at,user?.updated_at);
                if(user?.deleted_at!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_deleted_at,user?.deleted_at);
                if(user?.region_id!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_region_id,user?.region_id);
                if(user?.territory_id!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_territory_id,user?.territory_id);
                if(user?.brick_id!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_brick_id,user?.brick_id);
                if(user?.image!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_image,user?.image);
                if(user?.phone!=null)
                Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_phone,user?.phone);
               if(user?.email!=null) {
                   var startHomeUser = Intent(this@MainActivity, UserHomeActivity::class.java);
                   startActivity(startHomeUser);
                   finish();
               }else{
                   Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_TOKEN,"");
                   dialog.dismiss();
               }
}else{
    Paper.book(Prevalent.USER_BOOK).write(Prevalent.USER_TOKEN,null);
    dialog.dismiss();
}

            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Please check your connection to the internet",Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
        })

    }





    fun setProgressDialog(progressText:String) {
        val llPadding = 30
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam
        val progressBar = ProgressBar(this)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.progressTintList= ColorStateList.valueOf(Color.RED)
        };
        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(this)
        tvText.text = progressText;
        tvText.setTextColor(Color.parseColor("#000000"))

        tvText.textSize = 35f
        tvText.layoutParams = llParam
        tvText.typeface = face;
        ll.addView(progressBar)
        ll.addView(tvText)

        builder.setCancelable(false)
        builder.setView(ll)
        dialog = builder.create()

        dialog.show()
        val window: Window? = dialog.getWindow()
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.getWindow()!!.getAttributes())
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.getWindow()!!.setAttributes(layoutParams)
        }
    }

}