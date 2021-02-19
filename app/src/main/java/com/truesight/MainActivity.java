package com.truesight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.truesight.Adapter.VideoDetailsAdapter;
import com.truesight.Model.Item;
import com.truesight.Model.VideoDetails;
import com.truesight.Retrofit.GetDataService;
import com.truesight.Retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private VideoDetailsAdapter videoDetailsAdapter;
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);

        //DATA 1
        GetDataService dataService = RetrofitInstance.getRetrofit().create(GetDataService.class);

        Call<VideoDetails> videoDetailsRequest = dataService
                .getVideoData("snippet", "UCx0qCryognOczd5HN6ElI-w", "AIzaSyC5ihEmrE7sE8oXj2aCeFwsNdZ80C0HVig", "date","20");



        videoDetailsRequest.enqueue(new Callback<VideoDetails>() {
            @Override
            public void onResponse(Call<VideoDetails> call, Response<VideoDetails> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null){
                        Log.e(TAG,"response Sukses");
                        Toast.makeText(MainActivity.this, "LOADING, Please Wait", Toast.LENGTH_LONG).show();
                        setUpRecyclerView(response.body().getItems());
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoDetails> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e(TAG.concat("REQUEST API FAILED"), t.getMessage());
            }
        });






        //NAVIGATION BOTTOM BAR
        //Inisialisasi Variabel
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //memanggil itemselected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext()
                                ,Maps.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.tourney:
                        startActivity(new Intent(getApplicationContext()
                                ,Tourney.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

    }

    private void setUpRecyclerView(List<Item> items) {
        videoDetailsAdapter = new VideoDetailsAdapter( MainActivity.this, items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(videoDetailsAdapter);

    }
}