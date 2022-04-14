package com.ehabahmed.bayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ehabahmed.bayer.R;
import com.ehabahmed.bayer.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {
Context context;
ArrayList<Product> listItems;
ProductInterface productInterface;
    public ProductAdapter(Context context, ArrayList<Product> listItems) {
        this.context = context;
        this.listItems = listItems;
        if(context instanceof ProductInterface)
            productInterface= (ProductInterface) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.product_holder,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.productName.setText(listItems.get(position).getName());
//        holder.productPrice.setText(listItems.get(position).getPrice()+" EG");
        if(listItems.get(position).getQuantity()!=null)
        holder.quantity.setNumber(listItems.get(position).getQuantity());
    holder.quantity.setOnValueChangeListener((view, oldValue, newValue) -> {
        listItems.get(position).setQuantity(String.valueOf(newValue));
        if(productInterface!=null) {
            Product product=listItems.get(position);
            if(Integer.parseInt(product.getQuantity())>0)
            productInterface.onChangeQuantity(product,1);
            else if(Integer.parseInt(product.getQuantity())==0)
                productInterface.onChangeQuantity(product,0);
        }
    });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView productName;
//        TextView productPrice;
        ElegantNumberButton quantity;
        public Holder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
//            productPrice=itemView.findViewById(R.id.productPrice);
            quantity=itemView.findViewById(R.id.number_btn);
        }
    }


    public interface ProductInterface{
     void   onChangeQuantity(Product product,int type);
    }
    public void updateList(ArrayList<Product> newList){
        listItems=new ArrayList<>();
        listItems.addAll(newList);
        notifyDataSetChanged();
    }

}
