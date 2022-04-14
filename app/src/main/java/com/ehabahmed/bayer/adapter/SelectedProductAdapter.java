package com.ehabahmed.bayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ehabahmed.bayer.R;
import com.ehabahmed.bayer.model.Product;

import java.util.ArrayList;

public class SelectedProductAdapter extends RecyclerView.Adapter<SelectedProductAdapter.Holder> {
    Context context;
    ArrayList<Product> listItems;
    ProductAdapter.ProductInterface productInterface;

    public SelectedProductAdapter(Context context, ArrayList<Product> listItems) {
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
holder.productName.setText(listItems.get(position).getName());
// holder.productPrice.setText(listItems.get(position).getPrice()+" EG");
holder.productQuantity.setText("quantity : "+listItems.get(position).getQuantity());
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