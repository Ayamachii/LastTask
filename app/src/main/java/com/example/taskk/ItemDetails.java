package com.example.taskk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.taskk.databinding.ActivityItemDetailsBinding;
import com.example.taskk.models.Product;

public class ItemDetails extends AppCompatActivity {

    private static int counter = 0;
    ActivityItemDetailsBinding itemDetailsBinding;
    TextView counter_txtview;
    AppCompatButton plus_btn;
    AppCompatButton minus_btn;
    AppCompatButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        counter_txtview = findViewById(R.id.counter);
        plus_btn = findViewById(R.id.plus_btn);
        minus_btn = findViewById(R.id.minus_btn);
        back_btn = findViewById(R.id.backbtn_imgview_d);
//
//        Intent intent = getIntent();
//        intent.getExtras();
//
//        Bundle bundle = getIntent().getExtras();
//        Product product = new Product(bundle.getString("title"), bundle.getString("description"), bundle.getInt("price"), bundle.getDouble("rating"));
//        itemDetailsBinding.setProduct(product);

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counter_txtview.setText(String.valueOf(counter));
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter>0){
                    counter--;
                    counter_txtview.setText(String.valueOf(counter));
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(ItemDetails.this, MainActivity.class);
                startActivity(backIntent);
            }
        });




    }
}