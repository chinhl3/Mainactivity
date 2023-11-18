package com.example.mainactivity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mainactivity.R;
import com.example.mainactivity.Url.Url;
import com.example.mainactivity.activity.Car;
import com.example.mainactivity.interface1.IProductList;
import com.example.mainactivity.obj.san_pham;

import java.util.ArrayList;

public class sp_rcv_apdapter extends RecyclerView.Adapter<sp_rcv_apdapter.viewHolder> {
    Context context;
    ArrayList<san_pham> list;
    private IProductList iProductList;


    int vi_tri;
    int size;
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
        holder.them_vao_gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(context);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi_tri=holder.getAdapterPosition();
            }
        });
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
    public  void showCustomDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_gio_hang);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);
        ImageView hinh_dialog =dialog.findViewById(R.id.hinh_dialog);
        TextView gia_dialog=dialog.findViewById(R.id.gia_dialog);
        TextView so_luong_kho=dialog.findViewById(R.id.so_luong_kho);
        TextView mo_ta_dialog=dialog.findViewById(R.id.mo_ta_dialog);
        RadioGroup chon_co=dialog.findViewById(R.id.chon_co);
        RadioButton co_1=dialog.findViewById(R.id.co_1);
        RadioButton co_2=dialog.findViewById(R.id.co_2);
        RadioButton co_3=dialog.findViewById(R.id.co_3);
        Button them_gio_hang=dialog.findViewById(R.id.them_gio_hang);
        Glide.with(context)
                .load(Url.ROOT_URL+"uploads/" +list.get(vi_tri).getName_anh())
                .into(hinh_dialog);
        gia_dialog.setText(String.valueOf(list.get(vi_tri).getGia()));
        so_luong_kho.setText(String.valueOf(list.get(vi_tri).getSo_luong()));
        mo_ta_dialog.setText(list.get(vi_tri).getMo_ta());
        String ten_anh=list.get(vi_tri).getName_anh();
        co_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=40;
            }
        });
        co_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=39;
            }
        });
        co_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=41;
            }
        });
        them_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int gia = Integer.parseInt(gia_dialog.getText().toString());
                int sl = Integer.parseInt(so_luong_kho.getText().toString());
                String mt = mo_ta_dialog.getText().toString();
                String ten_sp=list.get(vi_tri).getTen_sp();
                san_pham dt1 = new san_pham();
                dt1.setTen_sp(ten_sp);
                dt1.setName_anh(ten_anh);
                dt1.setGia(gia);
                dt1.setSo_luong(sl);
                dt1.setMo_ta(mt);
                dt1.setKich_thuoc(String.valueOf(size));
                Log.d(">>>>", "têst: "+dt1.getKich_thuoc());
                Intent intent = new Intent(context, Car.class);

                // Chuyền đối tượng vào Intent
                intent.putExtra("dt1", (Parcelable) dt1);

                // Khởi chạy Activity
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
