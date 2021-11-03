package com.example.myfridge;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // constant for our FDA guidelines database
    // private static final String DB_NAME = "FDA_Storage.db";

    // variables that will be used globally throughout app
    public static Refrigerator theFridge;
    public static List<GroceryItem> theGroceryTypes;

    @SuppressLint("InlinedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
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

        // Set up our permissions code
        Dexter.withContext(this)
                .withPermission(
                        Manifest.permission.SCHEDULE_EXACT_ALARM)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        // Do Nothing
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        // Do Nothing
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        new AlertDialog.Builder(getApplicationContext()).setTitle("Permissions Request")
                                .setMessage("Permission needed to schedule alarms for grocery items")
                                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                                    dialog.dismiss();
                                    permissionToken.cancelPermissionRequest();
                                })
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                    dialog.dismiss();
                                    permissionToken.continuePermissionRequest();
                                })
                                .setOnDismissListener(dialog -> permissionToken.cancelPermissionRequest())
                                .show();
                    }
                }).check();

        Dexter.withContext(this)
                .withPermission(Manifest.permission.WAKE_LOCK)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        // Do Nothing
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        // Do Nothing
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        new AlertDialog.Builder(getApplicationContext()).setTitle("Permissions Request")
                                .setMessage("Permission needed to schedule alarms for grocery items")
                                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                                    dialog.dismiss();
                                    permissionToken.cancelPermissionRequest();
                                })
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                    dialog.dismiss();
                                    permissionToken.continuePermissionRequest();
                                })
                                .setOnDismissListener(dialog -> permissionToken.cancelPermissionRequest())
                                .show();
                    }
                }).check();


    }
}