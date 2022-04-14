package com.ehabahmed.bayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehabahmed.bayer.adapter.ProductAdapter
import com.ehabahmed.bayer.api.API
import com.ehabahmed.bayer.model.Product
import com.ehabahmed.bayer.model.Products
import com.ehabahmed.bayer.prevalent.Prevalent
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.R.menu
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable


class ProductsActivity : AppCompatActivity() , SearchView.OnQueryTextListener,ProductAdapter.ProductInterface {
    lateinit var productRecyclerView:RecyclerView;
    lateinit var products:ArrayList<Product>;
    lateinit var retrofit: Retrofit;
    lateinit var api: API;
    lateinit var adapter:ProductAdapter;
    lateinit var confirm: Button;
    lateinit var selectedItems:ArrayList<Product>;
    lateinit var pharmacyName:String;
    lateinit var pharmacyId:String;
    lateinit var location:String;
    lateinit var pharmacyTitle: TextView;
    lateinit var floatingActionButton: FloatingActionButton;
    lateinit var bottomAppBar: BottomAppBar;
    lateinit var progressBar:ProgressBar;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        pharmacyTitle=findViewById(R.id.pharmacy_title);
     supportActionBar?.setBackgroundDrawable(
         ColorDrawable(ContextCompat.getColor(this@ProductsActivity,R.color.purple_200)));
        supportActionBar?.setTitle("Bayer Direct");
        pharmacyName=intent.getStringExtra("pharmacyName").toString();
        pharmacyTitle.setText(pharmacyName);
      progressBar=findViewById(R.id.progressBar);
        pharmacyId=intent.getStringExtra("pharmacyId").toString();
        location=intent.getStringExtra("pharmacylocation").toString();
        productRecyclerView=findViewById(R.id.recycler_products);
        productRecyclerView.layoutManager=LinearLayoutManager(this@ProductsActivity);
       confirm=findViewById(R.id.next);
        selectedItems=ArrayList();
        confirm.setOnClickListener {


           if(selectedItems.size==0) {
               Toast.makeText(
                   this@ProductsActivity,
                   "please choose some products",
                   Toast.LENGTH_LONG
               ).show()
           }else {

               var startConfirmOrder = Intent(this@ProductsActivity, ConfirmActivity::class.java);
               startConfirmOrder.putExtra("orderList",selectedItems as Serializable)
               startConfirmOrder.putExtra("pharmacyName",pharmacyName)
               startConfirmOrder.putExtra("pharmacyId",pharmacyId)
               startConfirmOrder.putExtra("pharmacylocation",location)
               startActivity(startConfirmOrder);
           }
        }
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        getProducts();


    }

    private fun getProducts() {
        progressBar.visibility= View.VISIBLE;
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        api=retrofit.create(API::class.java)
        var nToken:String= Paper.book(Prevalent.USER_BOOK).read(Prevalent.USER_TOKEN);
        api.getProducts(nToken).enqueue(object:Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                products= response.body()?.data!!;
                adapter=ProductAdapter(this@ProductsActivity,products);
                productRecyclerView.adapter=adapter
                progressBar.visibility= View.INVISIBLE;
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@ProductsActivity,"Please check your connection to the internet",Toast.LENGTH_LONG).show()
                progressBar.visibility= View.INVISIBLE;
            }
        })


//        bottomAppBar=findViewById(R.id.bottomAppBar);

        floatingActionButton=findViewById(R.id.add);
        floatingActionButton.setOnClickListener {
          var startHomeUser=Intent(this@ProductsActivity,UserHomeActivity::class.java);
            startHomeUser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(startHomeUser)
        }

//        bottomAppBar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                if(item?.itemId==R.id.myRequest){
//
//                }
//                return true;
//            }
//        })






    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var userInput:String = newText?.lowercase().toString();
        var newlist =ArrayList<Product>();
        for (item in products) {
            if (item.getName().lowercase().contains(userInput)) {
                newlist.add(item);
            }
        }
        try{
            adapter.updateList(newlist);
        }catch (e:Exception){}
        return true;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search,menu);
        val menuItem = menu!!.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true;
    }

    override fun onChangeQuantity(product: Product,type:Int) {

        if(type==1){
         var check=   selectedItems.contains(product)

            if(check==true){
                var index=selectedItems.indexOf(product)
                selectedItems[index]=product;
            }else{
                selectedItems.add(product);
            }

        }else if(type==0){
            selectedItems.remove(product);
        }

    }





}