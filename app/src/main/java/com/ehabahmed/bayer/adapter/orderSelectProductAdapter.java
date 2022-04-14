package com.ehabahmed.bayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehabahmed.bayer.R;
import com.ehabahmed.bayer.model.ItemOrder;
import com.ehabahmed.bayer.model.Items;
import com.ehabahmed.bayer.model.Product;

import java.util.ArrayList;

public class orderSelectProductAdapter extends RecyclerView.Adapter<orderSelectProductAdapter.Holder> {


    Context context;
    ArrayList<ItemOrder> listItems;
    ProductAdapter.ProductInterface productInterface;

    public orderSelectProductAdapter(Context context, ArrayList<ItemOrder> listItems) {
        this.context = context;
        this.listItems = listItems;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.selected_order_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.productName.setText("name :    "+listItems.get(position).product_name);
// holder.productPrice.setText(listItems.get(position).getPrice()+" EG");
        holder.productQuantity.setText("quantity :  "+listItems.get(position).quantity);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView productName;
        //        TextView productPrice;
        TextView productQuantity;
        public Holder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            //   productPrice=itemView.findViewById(R.id.productPrice);
            productQuantity=itemView.findViewById(R.id.quantity);
        }
    }
}