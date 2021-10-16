package com.example.myfridge;

import static com.example.myfridge.MainActivity.theFridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This is the activity for viewing our Refrigerator contents
public class FridgeActivity extends AppCompatActivity {

    private FridgeAdapter adapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_view_fridge);

        recyclerView = (RecyclerView) findViewById(R.id.viewGroceryList);

        // Test Code
        //Date nowDate = new Date();
        //List<Item> testList = new ArrayList<Item>();
        //testList.add(new Item("TestName1", "TestType1", nowDate, nowDate ));
        //testList.add(new Item("TestName2", "TestType2", nowDate, nowDate ));
        //testList.add(new Item("TestName3", "TestType3", nowDate, nowDate ));

        //adapter = new FridgeAdapter(testList, getApplication());
        // End Test Code

        adapter = new FridgeAdapter(theFridge.get_Items(), FridgeActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FridgeActivity.this));
    }
}
