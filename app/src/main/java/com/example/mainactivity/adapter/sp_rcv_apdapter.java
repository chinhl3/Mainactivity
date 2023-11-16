package com.example.mainactivity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mainactivity.R;
import com.example.mainactivity.Url.Url;
import com.example.mainactivity.obj.san_pham;

import java.util.ArrayList;

public class sp_rcv_apdapter extends RecyclerView.Adapter<sp_rcv_apdapter.viewHolder> {
    Context context;
    ArrayList<san_pham> list;

    public sp_rcv_apdapter(Context context, ArrayList<san_pham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public sp_rcv_apdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sp_rcv_apdapter.viewHolder holder, int position) {
        Glide.with(context)
                .load(Url.ROOT_URL+"uploads/" +list.get(position).getName_anh())
                .into(holder.hinh_sp);
        holder.gia_sp.setText(String.valueOf(list.get(position).getGia())+"VND");
        Log.d(">>>>>", "hinh: "+Url.GETDATA_SP+"uploads/" +list.get(position).getName_anh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView hinh_sp;
        ImageButton them_vao_gio;
        TextView gia_sp;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hinh_sp=itemView.findViewById(R.id.hinh_sp);
            them_vao_gio=itemView.findViewById(R.id.them);
            gia_sp=itemView.findViewById(R.id.gia_sp);
        }
    }
}
