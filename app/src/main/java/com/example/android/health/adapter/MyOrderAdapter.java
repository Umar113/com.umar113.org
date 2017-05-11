package com.example.android.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.health.activities.MapsActivity;
import com.example.android.health.R;

import java.util.ArrayList;


public class MyOrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyorderData> arrData = new ArrayList<MyorderData>();
    private static LayoutInflater inflater = null;

    public MyOrderAdapter(Context context, ArrayList<MyorderData> arrData) {
        // TODO Auto-generated constructor stub
        this.arrData = arrData;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder {
        TextView doctorName;
        TextView hospital;
        TextView address;
        TextView exp;
        TextView contact;
        TextView faddress;
        LinearLayout item_click;



    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder holder;

        if (view == null) {

            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);


            holder.doctorName = (TextView) view.findViewById(R.id.doctorName);
            holder.hospital = (TextView) view.findViewById(R.id.hospital);
            holder.address = (TextView) view.findViewById(R.id.address);
            holder.exp = (TextView) view.findViewById(R.id.exp);
            holder.contact = (TextView) view.findViewById(R.id.contact);
            holder.faddress= (TextView) view.findViewById(R.id.faddress);
            holder.item_click= (LinearLayout) view.findViewById(R.id.item_click);



            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

		/*Picasso.with(context)
                .load(""+arrData.get(position).getImage())
				.placeholder(R.drawable.closeiconnew)
				.error(R.drawable.closeiconnew)
				.into(holder.imageView1);*/
        holder.doctorName.setText(arrData.get(position).getName());
        holder.hospital.setText(arrData.get(position).getHospital());
        holder.address.setText(arrData.get(position).getAddress());
        holder.exp.setText(arrData.get(position).getExp());
        holder.contact.setText(arrData.get(position).getContact());
       holder.faddress.setText(arrData.get(position).getFaddress());
//        holder.item_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                String add=arrData.get(position).getHospital().toString();
//                bundle.putString("KEY_ADDRESS",add);
//              //  Toast.makeText(context,arrData.get(position).getStatus().toString(),Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(context, MapsActivity.class);
//                i.putExtras(bundle);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });
        return view;

    }

}
