package com.example.taskk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskk.models.Product;
import com.example.taskk.models.Root;
import com.example.taskk.recycler.ProductsAdapter;
import com.example.taskk.recycler.RecyclerInterface;
import com.example.taskk.retrofit.ApiInterface;
import com.example.taskk.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {
    
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ProductsAdapter adapter;
    ArrayList<Product> productList;
    EditText editText;

    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.found_rv);
        editText = findViewById(R.id.searchprod_edttxt);
        recyclerView = findViewById(R.id.found_rv);

        productList = new ArrayList<>();
        search = String.valueOf(editText.getText());

        retrofitRecycle();


//        if(search == "laptops"){
//            int x = 0;
//            ArrayList<Product> laptopsList = new ArrayList<>();
//            for(int i = 0;i<productList.size();i++){
//                if(productList.get(i).getCategory()==search){
//                    laptopsList.add(productList.get(i));
//                    x++;
//
//                }
//            }
//            populateRecycler(laptopsList);
//        }

    }

    private void retrofitRecycle(){
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
                Toast.makeText(getApplicationContext(), "Failed to get items", Toast.LENGTH_LONG);
            }
        });

//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ItemDetails.class);
//                startActivity(intent);
//            }
//        });


    }

    private void populateRecycler(ArrayList<Product> productList) {

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductsAdapter(productList, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ItemDetails.class);

        intent.putExtra("name", productList.get(position).getTitle());
        intent.putExtra("description", productList.get(position).getDescription());
        intent.putExtra("rating", productList.get(position).getRating());
        intent.putExtra("price", productList.get(position).getPrice());
        intent.putExtra("thumbnail", productList.get(position).getThumbnail());

        startActivity(intent);
    }
}
