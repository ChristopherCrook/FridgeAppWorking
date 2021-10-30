package com.example.myfridge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GroceryItemTest {
    @Test
    public void ExecuteTest() {
        int days = 25;
        String name = "Ambrosia";

        GroceryItem item = new GroceryItem(name, days);

        assertEquals(item.GetType(), name);
        assertEquals(item.GetDays(), days);

        name = "Salad";
        days = days + 5;

        item.Set(name, days);

        assertEquals(item.GetType(), name);
        assertEquals(item.GetDays(), days);
    }
}
