package com.example.myfridge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView dateView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // On-click listener
            // XXX To-Do

            nameView = (TextView) itemView.findViewById(R.id.groceryName);
            dateView = (TextView) itemView.findViewById(R.id.groceryDate);
            view = itemView;
        }
    }

    private List<Item> fridgeItems;
    private Context context_t;

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
                        parent);

        ViewHolder theView = new ViewHolder(ItemView);

        return theView;
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeAdapter.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        holder.nameView.setText(fridgeItems.get(position).Get_Name());
        holder.dateView.setText(fridgeItems.get(position).Get_Date().toString());
    }

    @Override
    public int getItemCount() {
        return fridgeItems.size();
    }

    @Override
    public void onAttachedToRecyclerView( RecyclerView recycler) {
        super.onAttachedToRecyclerView(recycler);
    }
}
