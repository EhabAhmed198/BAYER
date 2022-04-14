package com.ehabahmed.bayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehabahmed.bayer.api.API
import com.ehabahmed.bayer.model.Pharmacy
import com.ehabahmed.bayer.model.PharmacyBranches
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
 * Use the [addNewOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addNewOrderFragment : Fragment() {
    lateinit var pharmacyRecyclerView:RecyclerView;
   lateinit var pharmacy: Pharmacy;
    lateinit var retrofit:Retrofit;
    lateinit var api: API;
    lateinit var adapter:com.ehabahmed.bayer.adapter.Pharmacy;
    lateinit var pharmacyNameEditText:EditText;
    lateinit var pharmacycodeEditText:EditText;
    lateinit var pharmacySearchButton: Button;
lateinit var progressBar:ProgressBar;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_add_new_order, container, false)

        pharmacyRecyclerView=view.findViewById(R.id.rey_pharmacy);
        pharmacyNameEditText=view.findViewById(R.id.name);
        pharmacycodeEditText=view.findViewById(R.id.code);
        pharmacySearchButton=view.findViewById(R.id.bt_search);
        progressBar=view.findViewById(R.id.progressbar);
pharmacy=Pharmacy();
        pharmacySearchButton.setOnClickListener {
            var name=pharmacyNameEditText.text.toString().lowercase().trim();
            var code=pharmacycodeEditText.text.toString().lowercase().trim();
            if(name.isEmpty() && code.isEmpty()){
                getPharmacy()

            }else if(!name.isEmpty() && code.isEmpty()){

                getPharmacySearch(name,code,1)
            }else if(name.isEmpty() && !code.isEmpty()){
                getPharmacySearch(name,code,2)

            }else if(!name.isEmpty() && !code.isEmpty()){
                getPharmacySearch(name,code,3)
            }
        }
        pharmacyRecyclerView.layoutManager=LinearLayoutManager(context);
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        Paper.init(context);
        try {
            getPharmacy();
        }catch (e:Exception){
            getPharmacy();
        }
        return view;
    }


    private fun getPharmacy() {
        progressBar.visibility=View.VISIBLE
        pharmacyRecyclerView.visibility=View.INVISIBLE
        retrofit= Retrofit.Builder().baseUrl("http://salesrep.direct")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        api=retrofit.create(API::class.java)
        var nToken:String=Paper.book(Prevalent.USER_BOOK).read(Prevalent.USER_TOKEN);

        api.getPharmacy(nToken).enqueue(object : Callback<Pharmacy> {
            override fun onResponse(call: Call<Pharmacy>, response: Response<Pharmacy>) {
                try {

                    pharmacy = response.body()!!;
                }catch (e:Exception){
                    getPharmacy();
                }
                if(pharmacy.data!=null) {
                    try {
                        adapter = com.ehabahmed.bayer.adapter.Pharmacy(context, pharmacy);
                    }catch (e:Exception){ getPharmacy()}
                } else {
                    getPharmacy()
                }
              try{  pharmacyRecyclerView.adapter=adapter}catch (e:Exception){ getPharmacy()}

                progressBar.visibility=View.INVISIBLE
                pharmacyRecyclerView.visibility=View.VISIBLE


            }

            override fun onFailure(call: Call<Pharmacy>, t: Throwable) {
                Toast.makeText(context,"no internet connection...", Toast.LENGTH_LONG).show()
                progressBar.visibility=View.INVISIBLE
                pharmacyRecyclerView.visibility=View.VISIBLE
                call.cancel()
                getPharmacy()
            }
        })


    }


    private fun getPharmacySearch(name:String,code:String,type:Int) {
        progressBar.visibility=View.VISIBLE;
        pharmacyRecyclerView.visibility=View.INVISIBLE
        var searchList=ArrayList<PharmacyBranches>()
//        var nPharmacy=pharmacy;
       var nPharmacy=Pharmacy();
//        retrofit= Retrofit.Builder().baseUrl("http://buyer.kantoegypt.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//        api=retrofit.create(API::class.java)
//        var nToken:String=Paper.book(Prevalent.USER_BOOK).read(Prevalent.USER_TOKEN);
//
//        api.getPharmacy(nToken).enqueue(object : Callback<Pharmacy> {
//            override fun onResponse(call: Call<Pharmacy>, response: Response<Pharmacy>) {
//                pharmacy= response.body()!!;
                for(item in pharmacy.data){
                    if(type==3) {
                        if (item.id.lowercase().contains(code) || item.name.lowercase().contentEquals(name)) {
                           searchList.add(item);

                        }
                    }else if(type==2){
                        if (item.id.lowercase().contains( code)) {
                           searchList.add(item);

                        }

                    }else if(type==1){
                        if ( item.name.lowercase().contains(name)) {
                          searchList.add(item);
                        }

                    }
                }
//                for( item in pharmacy.data){
//                    if(!searchList.contains(item)){
//                        searchList.add(item);
//                    }
//                }

                nPharmacy.data=searchList;
               if(nPharmacy.data.size>0)
                adapter=com.ehabahmed.bayer.adapter.Pharmacy(context,nPharmacy);
        else
            getPharmacy()
                pharmacyRecyclerView.adapter=adapter
                progressBar.visibility=View.INVISIBLE
                pharmacyRecyclerView.visibility=View.VISIBLE
//            }
//
//            override fun onFailure(call: Call<Pharmacy>, t: Throwable) {
//                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
//                progressBar.visibility=View.INVISIBLE
//                pharmacyRecyclerView.visibility=View.VISIBLE
//            }
//        })


    }


}