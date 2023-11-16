package com.example.mainactivity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mainactivity.R;
import com.example.mainactivity.Url.Url;
import com.example.mainactivity.adapter.banner_apdapter;
import com.example.mainactivity.adapter.sp_rcv_apdapter;
import com.example.mainactivity.banner.hinh;
import com.example.mainactivity.animation.ZoomOutPageTransformer;
import com.example.mainactivity.obj.san_pham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager2 viewPager2;
    ArrayList<hinh>list_hinh;
    CircleIndicator3 circleIndicator3;
    sp_rcv_apdapter apdapter;
    RecyclerView recyclerView;
    ArrayList<san_pham>list;
//    auto chuyen hinh
    private Handler handler = new Handler();
    private Runnable runnable= new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem()==list_hinh.size()-1){
                viewPager2.setCurrentItem(0);
            }else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        viewPager2=findViewById(R.id.viewPager2);
        circleIndicator3=findViewById(R.id.circleIndicator3);
        setSupportActionBar(toolbar);
//        tạo slider hinh
        list_hinh=lay_hinh();
        banner_apdapter banner_apdapter= new banner_apdapter(list_hinh);
        viewPager2.setAdapter(banner_apdapter);
        circleIndicator3.setViewPager(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

//        dua san pham vao rcv
        recyclerView=findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        list=new ArrayList<>();
        lay_du_lieu();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.search==item.getItemId()){
            Intent intent=new Intent(MainActivity.this, Search.class);
            startActivity(intent);
        }
        return true;
    }
    private ArrayList<hinh> lay_hinh(){
        ArrayList<hinh> list= new ArrayList<>();
        list.add(new hinh(R.drawable.baner1));
        list.add(new hinh(R.drawable.banner2));
        list.add(new hinh(R.drawable.banner3));
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
    public void lay_du_lieu(){
        JsonArrayRequest request= new JsonArrayRequest(Url.GETDATA_SP, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                for (int i =0 ;i<array.length();i++){
                    try {
                        JSONObject object= array.getJSONObject(i);
                        String ten_sp=object.getString("ten_sp").trim();
                        int gia=object.getInt("gia_sp");
                        int so_luong=object.getInt("so_luong_sp");
                        String hinh_anh=object.getString("hinh_sp").trim();
                        String kich_co=object.getString("kich_thuoc_sp").trim();
                        String mo_ta=object.getString("mo_ta").trim();
                        san_pham dt = new san_pham();
                        dt.setTen_sp(ten_sp);
                        dt.setGia(gia);
                        dt.setSo_luong(so_luong);
                        dt.setKich_thuoc(kich_co);
                        dt.setName_anh(hinh_anh);
                        dt.setMo_ta(mo_ta);
                        list.add(dt);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                apdapter=new sp_rcv_apdapter(MainActivity.this,list);
                recyclerView.setAdapter(apdapter);
                Log.d(">>>>>", "list: "+list.size());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d(">>>>>>", "noi: "+error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

}
