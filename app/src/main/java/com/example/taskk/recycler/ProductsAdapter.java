package com.example.taskk.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskk.ItemDetails;
import com.example.taskk.MainActivity;
import com.example.taskk.R;
import com.example.taskk.databinding.ActivityItemDetailsBinding;
import com.example.taskk.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Product> productsList;
    private Context context;
    ActivityItemDetailsBinding itemDetailsBinding;

    public ProductsAdapter(ArrayList<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prod_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        if(position==0){
            holder.prod_name.setText(String.valueOf("Found 10 Results"));
            holder.prod_name.setLines(2);
            holder.prod_name.setTextSize(25f);
            holder.cardView.setBackground(null);
            holder.fav_btn.setVisibility(View.GONE);
        }
        else {
            //holder.prod_img = productsList.get(position).getImages().get(0)
            holder.prod_price.setText("$" + Float.valueOf(productsList.get(position).getPrice()));
            holder.prod_name.setText(String.valueOf(productsList.get(position).getTitle()));
            holder.prod_descr.setText(String.valueOf(productsList.get(position).getDescription()));

            Picasso.get().load(productsList.get(position).getThumbnail()).into(holder.prod_img);
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos = holder.getAdapterPosition();
//                Intent toItem = new Intent(v.getContext(), ItemDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", String.valueOf(productsList.get(pos).title));
//                bundle.putString("description", String.valueOf(productsList.get(pos).description));
//                bundle.putInt("price", productsList.get(pos).price);
//                bundle.putDouble("rating", productsList.get(pos).rating);
//                bundle.putString("thumbnail", String.valueOf(productsList.get(pos).thumbnail));
//                toItem.putExtras(bundle);
//                Picasso.get().load(productsList.get(pos).getThumbnail()).into(itemDetailsBinding.prodImgD);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView prod_price;
        TextView prod_descr;
        TextView prod_name;
        ImageView prod_img;
        AppCompatButton fav_btn;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prod_price = itemView.findViewById(R.id.prod_price);
            prod_descr = itemView.findViewById(R.id.prod_descr);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_img = itemView.findViewById(R.id.prod_img);
            fav_btn = itemView.findViewById(R.id.fav_btn);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
