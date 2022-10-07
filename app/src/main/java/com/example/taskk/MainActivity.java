package com.example.taskk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.taskk.models.Product;
import com.example.taskk.models.Root;
import com.example.taskk.recycler.ProductsAdapter;
import com.example.taskk.retrofit.ApiInterface;
import com.example.taskk.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ProductsAdapter adapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.found_rv);
        productList = new ArrayList<>();

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getRoot().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if(root!=null) {
                    productList.addAll(root.getProducts());
                    populateRecycler(productList);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });

//        recyclerView = findViewById(R.id.found_rv);
//        manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
//        populateRecycler();
//        adapter = new ProductsAdapter(productList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        

    }

    private void populateRecycler(ArrayList<Product> productList) {

        manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductsAdapter(productList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

//    private void populateRecycler() {
//        RetrofitClient.getRetrofitClient().getRoot().enqueue(new Callback<Root>() {
//            @Override
//            public void onResponse(Call<Root> call, Response<Root> response) {
//                if(response.isSuccessful()&&response.body()!=null){
//
//                    productList.addAll(response.body().getProducts());
//                    adapter.notifyDataSetChanged();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Root> call, Throwable t) {
//
//            }
//        });
//    }
}