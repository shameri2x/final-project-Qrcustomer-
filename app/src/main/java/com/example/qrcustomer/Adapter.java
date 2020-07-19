package com.example.qrcustomer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {

    ArrayList<Stores> sArray;
    Context c;

    public Adapter(ArrayList<Stores> sArray, Context c) {
        this.sArray = sArray;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stores, parent, false);
        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((viewHolder) holder).name.setText(sArray.get(position).getName());
        ((viewHolder) holder).des.setText(sArray.get(position).getDescribe());
        ((viewHolder) holder).img.setImageResource(sArray.get(position).getImg());
        ((viewHolder) holder).evul.setText(sArray.get(position).getEvu() + "");
        ((viewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(c, Info.class);
                Info.store_name = sArray.get(position).getName();
                c.startActivity(n);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sArray.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView name;
        public TextView des;
        public TextView evul;
        public View view;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.imageView3);
            name = itemView.findViewById(R.id.textView2);
            des = itemView.findViewById(R.id.textView3);
            evul = itemView.findViewById(R.id.textView4);
        }


    }
}
