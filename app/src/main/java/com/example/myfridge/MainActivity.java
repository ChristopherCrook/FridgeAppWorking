package com.example.myfridge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "FDA_Storage.db";

    private static RefrigeratorHandler fridge_db;
    private static GroceryHandler grocery_db;

    public static Refrigerator theFridge;
    public static List<GroceryItem> theGroceryTypes;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the handler for the Refrigerator database
        fridge_db = new RefrigeratorHandler(getApplicationContext());

        // Create the handler for the Grocery Item database
        grocery_db = new GroceryHandler(getApplicationContext());

        theGroceryTypes = grocery_db.getAllGroceryTypes();

        // Create the Refrigerator to hold the users items
        theFridge = new Refrigerator(fridge_db);

        FloatingActionButton addGroceryButton = null;
        addGroceryButton = findViewById(R.id.addGroceryButton);
        addGroceryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), AddGroceryActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton addAlarmButton = null;
        addAlarmButton = findViewById(R.id.addAlarmButton);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), AddAlarmActivity.class);
                startActivity(intent);
            }
        });

        Button fridgeGoToButton = null;
        fridgeGoToButton = findViewById(R.id.fridgeButton);
        fridgeGoToButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), FridgeActivity.class);
                startActivity(intent);
            }
        });
    }
}