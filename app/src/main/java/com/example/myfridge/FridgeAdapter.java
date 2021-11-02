package com.example.myfridge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

// This is our Adapter class for handling Refrigerator Items in a RecyclerView
public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView dateView;
        public LinearLayout layout;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.ItemLayout);
            nameView = itemView.findViewById(R.id.groceryName);
            dateView = itemView.findViewById(R.id.groceryDate);
            view = itemView;
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

        // Set the Grocery Item Name
        holder.nameView.setText(fridgeItems.get(position).Get_Name());

        // Build the Expiration Date for View
        Calendar cal = Calendar.getInstance();
        cal.setTime(fridgeItems.get(position).Get_Expiration());

        String expires = new String();
        expires =
                getMonthFromInt(cal.get(Calendar.MONTH))
                        + " " + cal.get(Calendar.DAY_OF_MONTH)
                        + ", " + cal.get(Calendar.YEAR
        );

        holder.dateView.setText(expires);

        Date now = new Date();
        long expired = fridgeItems.get(position).Get_Expiration().getTime();

        // Check and see if the item has expired or about to
        if (expired < now.getTime())
            holder.layout.setBackgroundColor(Color.rgb(255,172,181));
        else if ((expired - now.getTime()) < 86400000)
            holder.layout.setBackgroundColor(Color.rgb(253, 242, 111));
        else
            holder.layout.setBackgroundColor(Color.WHITE);

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

    public String getMonthFromInt(int month) {
        String monthString = new String();
        if (month < 0 || month > 11)
            return null;

        switch (month) {
            case 0: monthString = "January";
                break;
            case 1: monthString = "February";
                break;
            case 2: monthString = "March";
                break;
            case 3: monthString = "April";
                break;
            case 4: monthString = "May";
                break;
            case 5: monthString = "June";
                break;
            case 6: monthString = "July";
                break;
            case 7: monthString = "August";
                break;
            case 8: monthString = "September";
                break;
            case 9: monthString = "October";
                break;
            case 10: monthString = "November";
                break;
            case 11: monthString = "December";
                break;
        }
        return monthString;
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
