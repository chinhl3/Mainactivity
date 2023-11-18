package com.example.mainactivity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainactivity.R;
import com.example.mainactivity.adapter.Cart_apdapter;
import com.example.mainactivity.adapter.sp_rcv_apdapter;
import com.example.mainactivity.interface1.IProductList;
import com.example.mainactivity.obj.san_pham;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Car extends AppCompatActivity  {

    Cart_apdapter apdapter;
    RecyclerView recyclerView_gio_hang;
    TextView tong_tien;
    ImageButton back;
    int tong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        recyclerView_gio_hang=findViewById(R.id.recyclerView_gio_hang);
        tong_tien=findViewById(R.id.tong_tien);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Car.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Lấy danh sách sản phẩm hiện có từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("products", MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("products", "");
        Gson gson = new Gson();
        ArrayList<san_pham> list2 = gson.fromJson(jsonString, new TypeToken<ArrayList<san_pham>>(){}.getType());

        // Thêm sản phẩm mới vào danh sách
        san_pham dt2 = new san_pham();
        dt2 = getIntent().getParcelableExtra("dt1");
        if (dt2 != null) {
            list2.add(dt2);
        }
        // Lưu danh sách đã cập nhật vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String updatedJsonString = gson.toJson(list2);
        editor.putString("products", updatedJsonString);
        editor.apply();
        Toast.makeText(this,""+list2.size(), Toast.LENGTH_SHORT).show();

        apdapter= new Cart_apdapter(this,list2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_gio_hang.setLayoutManager(linearLayoutManager);
        recyclerView_gio_hang.setAdapter(apdapter);
        for(int i=0;i<list2.size();i++){
            tong=tong+list2.get(i).getGia();
        }


        tong_tien.setText(String.valueOf(tong));


    }
    public void updateTongTien(Activity activity, int tongTien) {
        tong_tien.setText(String.valueOf(tongTien+tong)+"vnd");
    }


}