package com.example.myfridge;

import java.util.Date;

public final class GroceryItem {

    private String type;
    private int days;

    public GroceryItem(String type_m, int days_m)
    {
      type = new String(type_m);
      days = days_m;
    }

    public String GetType() { return type; }

    public int GetDays()
    {
        return days;
    }
}
