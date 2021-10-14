package com.example.myfridge;

import static com.example.myfridge.MainActivity.theFridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FridgeActivity extends AppCompatActivity {

    private FridgeAdapter adapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_view_fridge);

        recyclerView = (RecyclerView) findViewById(R.id.viewGroceryList);

        List<Item> theList = theFridge.get_Items();

        adapter = new FridgeAdapter(theList, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FridgeActivity.this));
    }
}
