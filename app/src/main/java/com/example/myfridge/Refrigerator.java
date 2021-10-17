package com.example.myfridge;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

// This is the class that holds our Refrigerator Items that are store in a
// database
public class Refrigerator {
    private List<Item> items;
    private int length;

    public Refrigerator(Context context)
    {
        RefrigeratorHandler handler = new RefrigeratorHandler(context);

        length = handler.getItemsCount();

        if (length < 1) // Database is empty
        {
            items = new ArrayList<>();
            return;
        }

        items = handler.getAllContacts();
    }

    public List<Item> get_Items()
    {
        return items;
    }

    public void AddItem(Item item, Context context)
    {
        RefrigeratorHandler handler = new RefrigeratorHandler(context);
        handler.Add_Item(item);

        // This is not an elegant solution, but it guarantees an ID number
        items.add(item);
        length = handler.getItemsCount();
    }

    public void RemoveItem(Item item, Context context)
    {
        RefrigeratorHandler handler = new RefrigeratorHandler(context);
        for (Item current : items)
        {
            if (current.Get_ID() == item.Get_ID())
            {
                System.out.println("I found it");
                items.remove(current);
                handler.deleteItem(item);
            }
        }
    }

    public int UpdateItem(Item item, Context context)
    {
        RefrigeratorHandler handler = new RefrigeratorHandler(context);
        int index = -1;
        for (Item current : items)
        {
            if (current.Get_ID() == item.Get_ID())
            {
                index = items.indexOf(current);
            }
        }

        if (index < 0) // Check and see if it was found
            return -1;

        items.set(index, item);
        handler.updateItem(item);
        return 0;
    }

    public Item GetItem(int id)
    {
        for (Item current : items)
        {
            if (current.Get_ID() == id)
            {
                return current;
            }
        }

        return null;
    }

}
