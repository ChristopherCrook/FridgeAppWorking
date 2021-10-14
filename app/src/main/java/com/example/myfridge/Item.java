package com.example.myfridge;

import androidx.annotation.NonNull;

import java.util.Date;

public class Item {

    private int id;
    private String name;
    private String type;
    private Date date;
    private Date expires;

    public Item(String name_m, String type_m, Date date_m, Date expires_m)
    {
        id = -1;
        name = name_m;
        type = type_m;
        date = date_m;
        expires = expires_m;
    }
    public Item(int id_m, String name_m, String type_m, Date date_m, Date expires_m)
    {
        id = id_m;
        name = name_m;
        type = type_m;
        date = date_m;
        expires = expires_m;
    }
    public int Get_ID() { return id; }

    public String Get_Name()
    {
        return name;
    }

    public Date Get_Date()
    {
        return date;
    }

    public String Get_Type()
    {
        return type;
    }

    public Date Get_Expiration() { return expires; }

    public void Set_Name(String name_m)
    {
        name = name_m;
    }

    public void Set_Date(Date date_m)
    {
        date = date_m;
    }

    public void Set_Expiration(Date date_m)
    {
        expires = date_m;
    }

    public void Set_Type(String type_m)
    {
        type = type_m;
    }
}
