package com.example.android.lagosdevelopers.controller;


import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lagosdevelopers.R;
import com.example.android.lagosdevelopers.api.Client;
import com.example.android.lagosdevelopers.api.Service;
import com.example.android.lagosdevelopers.itemAdapter;
import com.example.android.lagosdevelopers.model.item;
import com.example.android.lagosdevelopers.model.itemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView disconnected;
    private ClipData.Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadJASON();
                Toast.makeText(MainActivity.this, "Developers List Refreshed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Githubs Developers Names...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        LoadJASON();

    }

    private void LoadJASON() {
        disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client client = new Client();
            Service apiService = client.getClient().create(Service.class);
            Call<itemResponse> call = apiService.getItems();
            call.enqueue(new Callback<itemResponse>() {
                @Override
                public void onResponse(Call<itemResponse> call, Response<itemResponse> response) {
                    List<item>items = response.body().getItems();
                    recyclerView.setAdapter(new itemAdapter(getApplicationContext(),items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<itemResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fethching Data",Toast.LENGTH_LONG).show();
                    disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        }catch (Exception e){
            Log.d("Error",e.getMessage());
            Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}