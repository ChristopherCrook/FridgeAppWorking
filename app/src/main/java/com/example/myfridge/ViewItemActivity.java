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

        Calendar date_cal = Calendar.getInstance();
        date_cal.setTime(current.Get_Date());

        Calendar expire_cal = Calendar.getInstance();
        expire_cal.setTime(current.Get_Expiration());

        String date_string;
        String expire_string;

        date_string =
                FridgeAdapter.getMonthFromInt(date_cal.get(Calendar.MONTH))
                        + " " + date_cal.get(Calendar.DAY_OF_MONTH)
                        + ", " + date_cal.get(Calendar.YEAR);

        expire_string =
                FridgeAdapter.getMonthFromInt(expire_cal.get(Calendar.MONTH))
                        + " " + expire_cal.get(Calendar.DAY_OF_MONTH)
                        + ", " + expire_cal.get(Calendar.YEAR);

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
        showDate.setText(date_string);
        showExpire.setText(expire_string);
    }
}
