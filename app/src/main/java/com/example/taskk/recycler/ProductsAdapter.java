package com.example.taskk.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskk.R;
import com.example.taskk.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Product> productsList;
    private Context context;

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
        //holder.prod_img = productsList.get(position).getImages().get(0)
        holder.prod_price.setText(String.valueOf(productsList.get(position).getPrice()));
        holder.prod_name.setText(String.valueOf(productsList.get(position).getTitle()));
        holder.prod_descr.setText(String.valueOf(productsList.get(position).getDescription()));

        Picasso.get().load(productsList.get(position).getThumbnail()).into(holder.prod_img);

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prod_price = itemView.findViewById(R.id.prod_price);
            prod_descr = itemView.findViewById(R.id.prod_descr);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_img = itemView.findViewById(R.id.prod_img);
        }
    }
}
