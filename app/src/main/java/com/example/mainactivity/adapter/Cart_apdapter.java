package com.example.mainactivity.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mainactivity.R;
import com.example.mainactivity.Url.Url;
import com.example.mainactivity.activity.Car;
import com.example.mainactivity.obj.san_pham;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Cart_apdapter extends RecyclerView.Adapter<Cart_apdapter.viewHolder> {
    Context context;
    ArrayList<san_pham> list1;
    int tong_tien=0;
    int sl=1;
    int soLuongSP=1;
    int gia;



    public Cart_apdapter(Context context, ArrayList<san_pham> list) {
        this.context = context;
        this.list1 = list;
    }

    @NonNull
    @Override
    public Cart_apdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_apdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(Url.ROOT_URL+"uploads/" +list1.get(position).getName_anh())
                .into(holder.image);
        holder.name_sp.setText(list1.get(position).getTen_sp());
        holder.size_sp.setText(list1.get(position).getKich_thuoc());
        holder.sl_kho.setText(String.valueOf(list1.get(position).getSo_luong()));
        gia=Integer.parseInt(holder.so_luong_mua.getText().toString())*list1.get(position).getGia();
        holder.gia_sp_gio_hang.setText(String.valueOf(gia)+"vnd");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 soLuongSP = Integer.parseInt(holder.so_luong_mua.getText().toString());
            }
        });



        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(holder.so_luong_mua.getText().toString());
                if (sl < list1.get(position).getSo_luong()) {
                    sl++;
                    holder.so_luong_mua.setText(String.valueOf(sl));

                    int gia = Integer.parseInt(holder.so_luong_mua.getText().toString()) * list1.get(position).getGia();
                    holder.gia_sp_gio_hang.setText(String.valueOf(gia)+"vnd");

                    tong_tien += list1.get(position).getGia();

                    // Gọi hàm updateTongTien() từ lớp Activity
                    Car activity = (Car) context;
                    activity.updateTongTien(activity,tong_tien);
                } else {
                    Toast.makeText(context, "Số lượng sản phẩm đã hết", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(holder.so_luong_mua.getText().toString());
                if (sl > 0) {
                    sl--;
                    holder.so_luong_mua.setText(String.valueOf(sl));

                    int gia = Integer.parseInt(holder.so_luong_mua.getText().toString()) * list1.get(position).getGia();
                    holder.gia_sp_gio_hang.setText(String.valueOf(gia)+"vnd");

                    tong_tien -= list1.get(position).getGia();
                    Car activity = (Car) context;
                    activity.updateTongTien(activity,tong_tien);

                } else {
                    Toast.makeText(context, "Số lượng sản phẩm không thể nhỏ hơn 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name_sp,so_luong_mua,size_sp,gia_sp_gio_hang,sl_kho;
        ImageButton cong,tru,xoa;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_sp);
            name_sp=itemView.findViewById(R.id.name_sp);
            so_luong_mua=itemView.findViewById(R.id.so_luong_mua);
            size_sp=itemView.findViewById(R.id.size_sp);
            sl_kho=itemView.findViewById(R.id.sl_kho);
            gia_sp_gio_hang=itemView.findViewById(R.id.gia_sp_gio_hang);
            cong=itemView.findViewById(R.id.cong);
            tru=itemView.findViewById(R.id.tru);
            xoa=itemView.findViewById(R.id.xoa_sp);
        }
    }
    public void removeItem(int position) {

        // Tính toán tổng giá của sản phẩm cần xóa
        int giaSP = list1.get(position).getGia();

        int tongGiaSP = giaSP * soLuongSP;

        // Trừ tổng giá của sản phẩm cần xóa khỏi tổng giá hiện tại
        tong_tien -= tongGiaSP;

        // Xóa sản phẩm khỏi danh sách
        list1.remove(position);
//        tong_tien=0;
//        for (int i=0;i<list1.size();i++){
//            tong_tien=tong_tien+gia;
//        }

        // Lưu tổng giá mới vào SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("products", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String updatedJsonString = gson.toJson(list1);
        editor.putString("products", updatedJsonString);
        editor.apply();

        // Cập nhật tổng giá trên UI
        notifyDataSetChanged();
        // Gọi hàm updateTongTien() từ lớp Activity
        Car activity = (Car) context;
        activity.updateTongTien(activity, tong_tien);
    }


}
