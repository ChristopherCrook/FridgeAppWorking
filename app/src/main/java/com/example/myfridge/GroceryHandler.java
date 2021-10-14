package com.example.myfridge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GroceryHandler extends SQLiteOpenHelper {

    private List <GroceryItem> list_m;

    public GroceryHandler(Context context) {
        super(context, "temp", null, 1);
        list_m = new ArrayList<GroceryItem>();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<GroceryItem> getAllGroceryTypes(){
        GroceryItem chickenRaw = new GroceryItem("chicken, uncooked", 3);
        GroceryItem beefRaw = new GroceryItem("beef, uncooked", 3);
        GroceryItem beefDone = new GroceryItem("beef, cooked", 2);
        GroceryItem chickenDone = new GroceryItem("chicken, cooked", 2);
        GroceryItem milk = new GroceryItem("milk, dairy", 7);
        GroceryItem cheese = new GroceryItem("cheese, dairy", 14);
        GroceryItem eggs = new GroceryItem("eggs", 21);

        list_m.add(chickenRaw);
        list_m.add(chickenDone);
        list_m.add(beefRaw);
        list_m.add(beefDone);
        list_m.add(milk);
        list_m.add(cheese);
        list_m.add(eggs);

        return list_m;
    }

    public int getGroceryTypeCount() {
        return list_m.size();
    }
}
