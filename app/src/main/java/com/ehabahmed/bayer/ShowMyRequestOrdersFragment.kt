package com.ehabahmed.bayer

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehabahmed.bayer.adapter.ShowOrderAdapter
import com.ehabahmed.bayer.api.API
import com.ehabahmed.bayer.model.*
import com.ehabahmed.bayer.prevalent.Prevalent
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowMyRequestOrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowMyRequestOrdersFragment : Fragment() {
    lateinit var orderRecyclerView: RecyclerView;
    lateinit var adapter: ShowOrderAdapter;
    lateinit var retrofit: Retrofit;
    lateinit var api: API;


   lateinit var orderRootCompletModel: OrderRootCompletModel ;
    lateinit var token:String;
    lateinit var namePharmacySearch:EditText;
    lateinit var nameButtonSearch: Button;
    lateinit var progressBar:ProgressBar;
    lateinit var handler:Handler;
    lateinit var runnable:Runnable;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_show_my_request_orders, container, false)
        orderRecyclerView = view.findViewById(R.id.orders);
        progressBar = view.findViewById(R.id.pb);
        orderRecyclerView.layoutManager = LinearLayoutManager(context);
//        orderAndPharmacy = ArrayList();
//        orderAndPharmacy2 = ArrayList();

        handler = Handler(Looper.getMainLooper());


//        listItems = ArrayList();
        namePharmacySearch = view.findViewById(R.id.orderNameSearch);
        nameButtonSearch = view.findViewById(R.id.bt_search);

        nameButtonSearch.setOnClickListener {
            var name = namePharmacySearch.text.toString().lowercase().trim();

            if(name.isEmpty()){
          pushData()
            }else{
               var listItems=ArrayList<Datum>();
                for(item in orderRootCompletModel.data){
                    try {
                        var n=item.pharmacy_details.name.lowercase()

                        if (
                            name.contains(item.id.toString().lowercase().trim()) || n.contains(name)
                        ) {
                            listItems.add(item);
                        }
                    }catch (e:Exception ){}
                }
                orderRootCompletModel.data.clear()
                orderRootCompletModel.data=listItems;
                adapter= ShowOrderAdapter(context,orderRootCompletModel);
                orderRecyclerView.adapter=adapter;

            }
            Paper.init(context);





        }
        retrofit = Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        api = retrofit.create(API::class.java)
        pushData()
            return view;

    }
        private fun pushData() {
//            listItems = ArrayList();
//            listItems.clear()
            progressBar.visibility = View.VISIBLE;
            orderRecyclerView.visibility = View.INVISIBLE;
            token = Paper.book(Prevalent.USER_BOOK).read<String>(Prevalent.USER_TOKEN);
            api.getOrders(token).enqueue(object : Callback<OrderRootCompletModel> {
                override fun onResponse(
                    call: Call<OrderRootCompletModel>,
                    response: Response<OrderRootCompletModel>
                ) {
                    orderRootCompletModel = response.body()!!;
                    adapter = ShowOrderAdapter(context, orderRootCompletModel);
                    orderRecyclerView.adapter = adapter;
                    progressBar.visibility = View.INVISIBLE;
                    orderRecyclerView.visibility = View.VISIBLE;


                }

                override fun onFailure(call: Call<OrderRootCompletModel>, t: Throwable) {
                    Toast.makeText(context,"Please check your connection to the internet",Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.INVISIBLE;
                    orderRecyclerView.visibility = View.VISIBLE;
                }
            });

        }}



//    fun getPharmacyToOrder(listItems:ArrayList<OrderData>) {
//
//handler.post(Runnable {
//    kotlin.run {
//
//
//
//        for(orderData in listItems) {
//            var url = "http://buyer.kantoegypt.com/api/pharmacy/" + orderData.id;
//            api.getPharmacyOrder(token, url)
//                .enqueue(object : Callback
//                <PharmacyOrder> {
//                    override fun onResponse(
//                        call: Call<PharmacyOrder>,
//                        response: Response<PharmacyOrder>
//                    ) {
//                        if (response.isSuccessful) {
//                            var order = response.body() ;
//                            orderAndPharmacy.add(CustomOrderPharmacy(orderData, order))
//
//
//                                adapter=ShowOrderAdapter(context,orderAndPharmacy)
//                            orderRecyclerView.adapter=adapter;
//                                progressBar.visibility = View.INVISIBLE;
//                                orderRecyclerView.visibility = View.VISIBLE;
//                                orderAndPharmacy2 = orderAndPharmacy;
//
//
//
//                        }
//                    }
//
//                    override fun onFailure(call: Call<PharmacyOrder>, t: Throwable) {
//                        Toast.makeText(context, "no internet connection", Toast.LENGTH_LONG).show()
//                        progressBar.visibility = View.INVISIBLE;
//                        call.cancel()
//                    }
//                })
//        }
//    }
//});
//
//
//    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        return true;
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        var userInput:String = newText?.lowercase().toString();
//        var newlist =ArrayList<CustomOrderPharmacy>();
//        for (item in orderAndPharmacy) {
//            if (item.pharmacy.data.getName().lowercase().contains(userInput)) {
//                newlist.add(item);
//            }
//        }
//        try{
//            if(newlist.size>0)
//            adapter.updateList(newlist);
//
//        }catch (e:Exception){}
//        return true;
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        return inflater.inflate(R.menu.search,menu);
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true);
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//
//    }
//
//    override fun onStop() {
//        super.onStop()
//
//    }

