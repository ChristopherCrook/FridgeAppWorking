package com.example.myfridge;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator {
    private List<Item> items;
    private int length;

    private RefrigeratorHandler handler;

    public Refrigerator(RefrigeratorHandler extern_handler)
    {
        handler = extern_handler;

        length = handler.getItemsCount();

        if (length < 1) // Database is empty
        {
            items = new ArrayList<Item>();
            return;
        }

        items = handler.getAllContacts();
    }

    public List<Item> get_Items()
    {
        return items;
    }

    public void AddItem(Item item)
    {
        handler.Add_Item(item);

        items.clear();
        items = handler.getAllContacts(); // This is so we get an ID
                                          // for the new item
    }

    public void RemoveItem(Item item)
    {
        for (Item current : items)
        {
            if (current.Get_ID() == item.Get_ID())
            {
                items.remove(current);
                handler.deleteItem(item);
            }
        }
    }

    public int UpdateItem(Item item)
    {
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
}
