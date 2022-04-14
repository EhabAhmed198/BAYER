package com.ehabahmed.bayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehabahmed.bayer.adapter.SelectedProductAdapter
import com.ehabahmed.bayer.adapter.orderSelectProductAdapter
import com.ehabahmed.bayer.model.ItemOrder
import com.ehabahmed.bayer.model.Items
import com.ehabahmed.bayer.model.OrderRootCompletModel
import com.ehabahmed.bayer.model.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

class DetailsOrderActivity : AppCompatActivity() {
    lateinit var pharamacyName:String;
    lateinit var title:TextView;
    lateinit var listItems:ArrayList<ItemOrder>;
    lateinit var selectProductRecyclerView: RecyclerView;
    lateinit var adapter: orderSelectProductAdapter;
    lateinit var floatingActionButton: FloatingActionButton;
    lateinit var requestChooseOption:TextView;
    lateinit var noteTextView:TextView;
   lateinit var note1:String;
    lateinit var status:String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_order)
       title=findViewById(R.id.pharmacy_title);

        pharamacyName = intent.extras?.getString("pharamacyName").toString();
        listItems = intent.getSerializableExtra("listOrder" )as ArrayList<ItemOrder>;
     status=intent.extras?.getInt("request").toString();
        note1=intent.extras?.getString("note").toString();
       selectProductRecyclerView=findViewById(R.id.selected_order);
        requestChooseOption=findViewById(R.id.statusTextView);
        noteTextView=findViewById(R.id.noteTextView)

        if(!note1.equals("null")){
            noteTextView.setText("Note : \n    "+note1);
        }

        if(status.equals("1")){
            requestChooseOption.setText("Type : "+"Ordered by phone")
        }else if(status.equals("0")){
            requestChooseOption.setText("Type : "+"New request")
        }
        selectProductRecyclerView.layoutManager= LinearLayoutManager(this@DetailsOrderActivity);

        adapter=orderSelectProductAdapter(this@DetailsOrderActivity,listItems);
       selectProductRecyclerView.adapter=adapter;

        title.setText(pharamacyName);


    }
}