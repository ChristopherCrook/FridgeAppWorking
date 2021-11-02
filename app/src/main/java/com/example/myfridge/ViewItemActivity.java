package com.example.myfridge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

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

        String name = current.Get_Name();
        String type = current.Get_Type();
        String date = current.Get_Date().toString();
        String expire = current.Get_Expiration().toString();

        TextView showName;
        TextView showType;
        TextView showDate;
        TextView showExpire;

        showName = findViewById(R.id.nameViewField);
        showType = findViewById(R.id.typeViewField);
        showDate = findViewById(R.id.boughtOnViewField);
        showExpire = findViewById(R.id.expiresOnViewField);

        showName.setText(name);
        showType.setText(type);
        showDate.setText(date);
        showExpire.setText(expire);

        FloatingActionButton alarmButton;
        alarmButton = findViewById(R.id.addAlarmItemViewButton);
        alarmButton.setOnClickListener(view -> {
            Intent intent1;
            intent1 = new Intent(view.getContext(), AddAlarmActivity.class);
            intent1.putExtra("Name", name);
            intent1.putExtra("Expiration", current.Get_Expiration().getTime());
            intent1.putExtra("ID", current.Get_ID());
            startActivity(intent1);
        });
    }
}
