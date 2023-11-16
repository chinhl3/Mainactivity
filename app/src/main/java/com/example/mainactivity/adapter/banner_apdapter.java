package com.example.mainactivity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;
import com.example.mainactivity.banner.hinh;

import java.util.ArrayList;

public class banner_apdapter extends RecyclerView.Adapter<banner_apdapter.hinhanh> {
    private ArrayList<hinh> list;

    public banner_apdapter(ArrayList<hinh> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public banner_apdapter.hinhanh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_baner,parent,false);
        return new hinhanh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull banner_apdapter.hinhanh holder, int position) {
        hinh dt =list.get(position);
        holder.banner.setImageResource(dt.getHinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hinhanh extends RecyclerView.ViewHolder {
        ImageView banner;
        public hinhanh(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.hinh_banner);
        }
    }
}
