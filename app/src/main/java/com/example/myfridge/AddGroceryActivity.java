package com.example.myfridge;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddGroceryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selected_type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_add);

        Spinner typeSpinner = (Spinner) findViewById(R.id.spinnerGroceryType);

        typeSpinner.setOnItemSelectedListener(this);

        List<String> groceries = new ArrayList<String>();

        // Get the list of grocery types for the typeSpinner
        // This works
        for (GroceryItem item : MainActivity.theGroceryTypes)
        {
            groceries.add(item.GetType());
        }

        ArrayAdapter<String> typeSinnerAdapter = new ArrayAdapter<String>(
        this,
                android.R.layout.simple_spinner_item,
                groceries
        );

        typeSinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeSinnerAdapter);

        Button addButton = null;
        addButton = findViewById(R.id.addGroceryItemButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int expire_days = 0;
                long ms_time = 86400000;

                // Get the Item Name
                EditText itemText = (EditText) findViewById(R.id.inputGroceryName);
                String itemName = itemText.getText().toString();

                // Get the Item date
                EditText dateText = (EditText) findViewById(R.id.editTextDate);
                String itemDate = dateText.getText().toString();

                Date dateObject;

                // Try and retrieve the date. If unsuccessful, assign today's date
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.
                try {
                    dateObject = formatter.parse(itemDate);
                } catch (ParseException e) {
                    dateObject = new Date();
                }

                // Find the Grocery Type
                for (GroceryItem item : MainActivity.theGroceryTypes)
                {
                    if (item.GetType().equals(selected_type))
                    {
                        expire_days = item.GetDays();
                    }
                }

                // Calculate expiration Date
                ms_time = ms_time * expire_days;
                ms_time = ms_time + dateObject.getTime();

                Date expiration = new Date(ms_time);

                // Add new Item
                Item newItem = new Item(itemName, selected_type, dateObject, expiration);
                MainActivity.theFridge.AddItem(newItem, AddGroceryActivity.this);

                // Return to MainActivity
                finish();
            }
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
