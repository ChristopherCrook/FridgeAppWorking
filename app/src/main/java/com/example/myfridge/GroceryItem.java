package com.example.myfridge;

public final class GroceryItem {

    private String type;
    private int days;

    public GroceryItem(String type_m, int days_m)
    {
      type = type_m;
      days = days_m;
    }

    public void Set(String type_m, int days_m)
    {
      type = type_m;
      days = days_m;
    }

    public String GetType() { return type; }

    public int GetDays()
    {
        return days;
    }
}
