package com.example.myfridge;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddGroceryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selected_type;
    private CalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_add);

        // Create the Spinner variable for the grocery item types
        Spinner typeSpinner = findViewById(R.id.spinnerGroceryType);

        typeSpinner.setOnItemSelectedListener(this);

        List<String> groceries = new ArrayList<>();

        // Get the list of grocery types for the typeSpinner
        // This works
        for (GroceryItem item : MainActivity.theGroceryTypes)
        {
            groceries.add(item.GetType());
        }

        ArrayAdapter<String> typeSinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                groceries
        );

        typeSinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeSinnerAdapter);

        // Set the CalendarView variable
        calendar = findViewById(R.id.PurchasedCalendarView);

        // Set up the ADD GROCERY button specifics
        Button addButton;
        addButton = findViewById(R.id.addGroceryItemButton);
        addButton.setOnClickListener(view -> {
            int expire_days = 0;
            long ms_time = 86400000;

            // Get the Item Name
            EditText itemText = findViewById(R.id.inputGroceryName);
            String itemName = itemText.getText().toString();

            // Find the Grocery Type
            for (GroceryItem item : MainActivity.theGroceryTypes)
            {
                if (item.GetType().equals(selected_type))
                {
                    expire_days = item.GetDays();
                }
            }

            // Handle the date fields
            Date bought;
            Date expiration;

            bought = new Date(calendar.getDate());

            // Calculate expiration Date
            ms_time = ms_time * expire_days;
            ms_time = ms_time + bought.getTime();

            expiration = new Date(ms_time);

            // Add new Item
            Item newItem = new Item(itemName, selected_type, bought, expiration);
            MainActivity.theFridge.AddItem(newItem, AddGroceryActivity.this);

            // Return to MainActivity
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selected_type = null;
    }
}
