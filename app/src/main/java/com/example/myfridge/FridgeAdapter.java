package com.example.myfridge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

// This is our Adapter class for handling Refrigerator Items in a RecyclerView
public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView dateView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.groceryName);
            dateView = itemView.findViewById(R.id.groceryDate);
            view = itemView;
        }

        public void SetBackgroundColor(int color) {
            view.setBackgroundColor(color);
        }
    }

    private final List<Item> fridgeItems;
    private final Context context_t;

    public FridgeAdapter(List<Item> items_list, Context context_m){

        fridgeItems = items_list;
        context_t = context_m;
    }

    @NonNull
    @Override
    public FridgeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View ItemView = inflater
                .inflate(R.layout.grocery_recyclerview_layout,
                        parent,
                        false);

        return new ViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeAdapter.ViewHolder holder, int position) {

        holder.nameView.setText(fridgeItems.get(position).Get_Name());
        holder.dateView.setText(fridgeItems.get(position).Get_Expiration().toString());

        Date now = new Date();
        long expired = fridgeItems.get(position).Get_Expiration().getTime();
        if (expired < now.getTime())
            holder.SetBackgroundColor(Color.rgb(255,172,181));

        holder.itemView.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(view.getContext(), ViewItemActivity.class);
            intent.putExtra(
                    "Item_ID",
                    fridgeItems.get(holder.getAdapterPosition()).Get_ID()
            );
            context_t.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fridgeItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recycler) {
        super.onAttachedToRecyclerView(recycler);
    }
}
