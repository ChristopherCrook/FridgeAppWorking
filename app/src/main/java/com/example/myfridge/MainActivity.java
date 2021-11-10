package com.example.myfridge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // constant for our FDA guidelines database
    // private static final String DB_NAME = "FDA_Storage.db";

    // variables that will be used globally throughout app
    public static Refrigerator theFridge;
    public static List<GroceryItem> theGroceryTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the handler for the Grocery Item database and get contents
        GroceryHandler grocery_db;
        grocery_db      = new GroceryHandler(MainActivity.this);
        theGroceryTypes = grocery_db.getAllGroceryTypes();

        // Create the Refrigerator to hold the users items
        theFridge = new Refrigerator(MainActivity.this);

        // Set up our Add-Grocery Button
        FloatingActionButton addGroceryButton;
        addGroceryButton = findViewById(R.id.addGroceryButton);
        addGroceryButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(view.getContext(), AddGroceryActivity.class);
            startActivity(intent);
        });

        // Set up our button that takes us to the Fridge
        Button fridgeGoToButton;
        fridgeGoToButton = findViewById(R.id.fridgeButton);
        fridgeGoToButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(view.getContext(), FridgeActivity.class);
            startActivity(intent);
        });
    }
}