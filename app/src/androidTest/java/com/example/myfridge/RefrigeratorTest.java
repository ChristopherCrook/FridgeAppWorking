package com.example.myfridge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RefrigeratorTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myfridge", appContext.getPackageName());

        Refrigerator testFridge = new Refrigerator(appContext);

        List<Item> fridgeItems = testFridge.get_Items();
        List<Item> defaults = getItemsForTest();

        if (fridgeItems.isEmpty())
        {
            for (Item item : defaults)
                testFridge.AddItem(item, appContext);
        }

        for (Item item : fridgeItems)
        {
            // Test GetItem
            Item test = testFridge.GetItem(item.Get_ID());
            assertEquals(test.Get_ID(), item.Get_ID());
            assertEquals(test.Get_Name(), item.Get_Name());
            assertEquals(test.Get_Type(), item.Get_Type());
            assertEquals(test.Get_Date().getTime(), item.Get_Date().getTime());
            assertEquals(test.Get_Expiration().getTime(), item.Get_Expiration().getTime());

            // Test UpdateItem
            test.Set_Expiration(new Date(test.Get_Expiration().getTime() + 100));
            int i = testFridge.UpdateItem(test, appContext);
            assertEquals(i, 0);
            test = testFridge.GetItem(test.Get_ID());
            assertEquals(test.Get_Expiration().getTime(), item.Get_Expiration().getTime() + 100);

            // Test RemoveItem
            testFridge.RemoveItem(item, appContext);
            Item nullptr = testFridge.GetItem(item.Get_ID());
            assertNull(nullptr);

            // Test AddItem
            testFridge.AddItem(item, appContext);
        }
    }

    List<Item> getItemsForTest() {
        ArrayList<Item> theList = new ArrayList<>();

        Date s = new Date();
        Date e = new Date(s.getTime() + 1000);
        Item i1 = new Item("Test1", "Test1Type", s, e);
        Item i2 = new Item("Test2", "Test2Type", s, e);

        theList.add(i1);
        theList.add(i2);

        return theList;
    }
}
