package com.example.taskk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taskk.databinding.ActivityItemDetailsBinding;
import com.example.taskk.models.Product;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.squareup.picasso.Picasso;

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

        String title = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        double rating = getIntent().getDoubleExtra("rating", 0);
        int price = getIntent().getIntExtra("price", 0);
        String thumbnail = getIntent().getStringExtra("thumbnail");

        TextView prod_name = findViewById(R.id.item_name_d);
        TextView prod_descr = findViewById(R.id.item_descr_d);
        TextView prod_price = findViewById(R.id.item_price_d);
        TextView prod_rating = findViewById(R.id.item_rating_d);
        ImageView prod_thumbnail = findViewById(R.id.prod_img_d);

        prod_name.setText(title);
        prod_descr.setText(description);
        prod_price.setText("$"+Float.toString((float)price));
        prod_rating.setText(Double.toString(rating));

        Picasso.get().load(thumbnail).into(prod_thumbnail);
        //Picasso.with(activity).load(url).transform(new CircleTransform()).into(imageView);



        counter_txtview = findViewById(R.id.counter);
        plus_btn = findViewById(R.id.plus_btn);
        minus_btn = findViewById(R.id.minus_btn);
        back_btn = findViewById(R.id.backbtn_imgview_d);

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