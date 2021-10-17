package com.example.myfridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ViewItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("Item_ID", -1);

        if (id < 0) // We have an invalid id
            finish();

        Item current = MainActivity.theFridge.GetItem(id);

        TextView showName;
        TextView showType;
        TextView showDate;
        TextView showExpire;

        showName = findViewById(R.id.nameViewField);
        showType = findViewById(R.id.typeViewField);
        showDate = findViewById(R.id.boughtOnViewField);
        showExpire = findViewById(R.id.expiresOnViewField);

        showName.setText(current.Get_Name());
        showType.setText(current.Get_Type());
        showDate.setText(current.Get_Date().toString());
        showExpire.setText(current.Get_Expiration().toString());

        FloatingActionButton deleteButton;
        deleteButton = findViewById(R.id.DeleteItemButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
