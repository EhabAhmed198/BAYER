package com.ehabahmed.bayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehabahmed.bayer.DetailsOrderActivity;
import com.ehabahmed.bayer.R;
import com.ehabahmed.bayer.model.CustomOrderPharmacy;
import com.ehabahmed.bayer.model.OrderData;
import com.ehabahmed.bayer.model.OrderRootCompletModel;
import com.ehabahmed.bayer.model.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowOrderAdapter extends RecyclerView.Adapter<ShowOrderAdapter.Holder> {
    Context context;
   OrderRootCompletModel listItems;
  int gPosition=0;
    public ShowOrderAdapter(Context context, OrderRootCompletModel listItems) {
        this.context = context;
        this.listItems = listItems;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.show_oder_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
     holder.orderId.setText("Order number : "+listItems.data.get(position).id);
     holder.coutOrderItems.setText("Items  : "+listItems.data.get(position).items.size());


try {

    if (listItems.data.get(position).pharmacy_details.name != null)
        holder.pharmacyName.setText(listItems.data.get(position).pharmacy_details.name);

    if (listItems.data.get(position).pharmacy_details.other_name != null)
        holder.pharmacyOtherName.setText("other name : " + listItems.data.get(position).pharmacy_details.other_name);
//        Glide.with(context).load(listItems.getData().get(position).getImage()).error(R.drawable.logo).into(holder.pharmacyLogo);
    if (listItems.data.get(position).pharmacy_details.id != null)
        holder.pharmacyCode.setText("Id                    " + listItems.data.get(position).pharmacy_details.id);

    if (listItems.data.get(position).pharmacy_details.contact_name != null)
        holder.pharmacyContectName.setText("owner          " + listItems.data.get(position).pharmacy_details.contact_name);

    if (listItems.data.get(position).pharmacy_details.contact_phone != null)
        holder.pharmacyPhone.setText("Phone          " + listItems.data.get(position).pharmacy_details.contact_phone);

    if (listItems.data.get(position).pharmacy_details.address != null)
        holder.pharmacyAddresss.setText("address          " + listItems.data.get(position).pharmacy_details.address);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                Intent startDetailsOrder = new Intent(context, DetailsOrderActivity.class);

                startDetailsOrder.putExtra("pharamacyName", listItems.data.get(holder.getAdapterPosition()).pharmacy_details.name);
                startDetailsOrder.putExtra("listOrder", (Serializable) listItems.data.get(holder.getAdapterPosition()).items);
                startDetailsOrder.putExtra("request", listItems.data.get(holder.getAdapterPosition()).order_type);
                startDetailsOrder.putExtra("note", listItems.data.get(holder.getAdapterPosition()).notes);
                context.startActivity(startDetailsOrder);
            }catch(Exception e){}
        }
    });

}catch (Exception e){}


    }

    @Override
    public int getItemCount() {
        return listItems.data.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView orderId;
        TextView coutOrderItems;
        TextView pharmacyName;
        TextView pharmacyOtherName;
//        ImageView pharmacyLogo;

        TextView pharmacyCode;
        TextView pharmacyContectName;
        TextView pharmacyPhone;
        TextView pharmacyAddresss;


        public Holder(@NonNull View itemView) {
            super(itemView);
            orderId=itemView.findViewById(R.id.orderId);
            coutOrderItems=itemView.findViewById(R.id.orderItems);

            pharmacyName=itemView.findViewById(R.id.name);
            pharmacyOtherName=itemView.findViewById(R.id.otherName);
//            pharmacyLogo=itemView.findViewById(R.id.pharmacy_image);
            pharmacyCode=itemView.findViewById(R.id.code);
            pharmacyContectName=itemView.findViewById(R.id.cotactName);
            pharmacyPhone=itemView.findViewById(R.id.cotactPhone);
            pharmacyAddresss=itemView.findViewById(R.id.pharmacyaddress);
        }
    }


//    public void updateList(ArrayList<CustomOrderPharmacy> newList){
//        listItems=new ArrayList<>();
//        listItems.addAll(newList);
//        notifyDataSetChanged();
//    }
}
