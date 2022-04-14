package com.ehabahmed.bayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ehabahmed.bayer.ProductsActivity;
import com.ehabahmed.bayer.R;


import java.util.ArrayList;

public class Pharmacy extends RecyclerView.Adapter<Pharmacy.Holder> {
Context context;
com.ehabahmed.bayer.model.Pharmacy listItems;
    public Pharmacy(Context context, com.ehabahmed.bayer.model.Pharmacy listItems) {
        this.context = context;
        this.listItems = listItems;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.pharmacy_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(listItems.getData().get(position).getName()!=null)
        holder.pharmacyName.setText(listItems.getData().get(position).getName());
        if(listItems.getData().get(position).getOther_name()!=null)
        holder.pharmacyOtherName.setText("other name : "+listItems.getData().get(position).getOther_name());
//        Glide.with(context).load(listItems.getData().get(position).getImage()).error(R.drawable.logo).into(holder.pharmacyLogo);
        if(listItems.getData().get(position).getId()!=null)
            holder.pharmacyCode.setText("Id                    "+listItems.getData().get(position).getId());

        if(listItems.getData().get(position).getContact_name()!=null)
            holder.pharmacyContectName.setText("owner          "+listItems.getData().get(position).getContact_name());

        if(listItems.getData().get(position).getContact_phone()!=null) {
            holder.pharmacyPhone.setVisibility(View.VISIBLE);
            holder.pharmacyPhone.setText("Phone          " + listItems.getData().get(position).getContact_phone());
        }else{holder.pharmacyPhone.setVisibility(View.GONE);}
        if(listItems.getData().get(position).getAddress()!=null)
            holder.pharmacyAddresss.setText("address          "+listItems.getData().get(position).getAddress());


        int positionGlobal=position;
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent pharmacyStartIntent=new Intent(context, ProductsActivity.class);
        pharmacyStartIntent.putExtra("pharmacyName",listItems.getData().get(positionGlobal).getName());
        pharmacyStartIntent.putExtra("pharmacyId",listItems.getData().get(positionGlobal).getId());
        pharmacyStartIntent.putExtra("pharmacylocation",listItems.getData().get(positionGlobal).getLocation());
        context.startActivity(pharmacyStartIntent);
    }
});
    }

    @Override
    public int getItemCount() {
        return listItems.getData().size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView pharmacyName;
        TextView pharmacyOtherName;
//        ImageView pharmacyLogo;

        TextView pharmacyCode;
        TextView pharmacyContectName;
        TextView pharmacyPhone;
        TextView pharmacyAddresss;


        public Holder(@NonNull View itemView) {
            super(itemView);
            pharmacyName=itemView.findViewById(R.id.name);
            pharmacyOtherName=itemView.findViewById(R.id.otherName);
//            pharmacyLogo=itemView.findViewById(R.id.pharmacy_image);
            pharmacyCode=itemView.findViewById(R.id.code);
            pharmacyContectName=itemView.findViewById(R.id.cotactName);
            pharmacyPhone=itemView.findViewById(R.id.cotactPhone);
            pharmacyAddresss=itemView.findViewById(R.id.pharmacyaddress);
        }
    }
}
