package com.example.myfridge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;

public class ItemTest {
    @Test
    public void ExecuteTest()
    {
        Date bought = new Date();
        Date expires = new Date(bought.getTime() + 1000);
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

        assertEquals(
                "Testing Date",
                bought.getTime(),
                item.Get_Date().getTime()
        );
        assertEquals(
                "Testing Expiration",
                expires.getTime(),
                item.Get_Expiration().getTime()
        );
    }
}
