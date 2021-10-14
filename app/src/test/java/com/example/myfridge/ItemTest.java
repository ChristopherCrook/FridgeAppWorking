package com.example.myfridge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;

public class ItemTest {
    @Test
    void ExecuteTest()
    {
        Date bought = new Date(2021, 06, 04);
        Date expires = new Date(2021, 06, 10);
        Item item = new Item(
                1,
                "Test Item",
                "Test Type",
                bought,
                expires
        );

        assertEquals(1, item.Get_ID());
        assertEquals("Testing Name", "Test Item", item.Get_Name());
        assertEquals("Testing Type", "Test Type", item.Get_Type());

        String bought_string = bought.toString();
        String expire_string = expires.toString();

        assertEquals("Testing Date", bought_string, item.Get_Date());
        assertEquals("Testing Expiration", expire_string, item.Get_Expiration());


    }
}
