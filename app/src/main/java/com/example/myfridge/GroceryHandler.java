package com.example.myfridge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GroceryHandler extends SQLiteOpenHelper {

    private List <GroceryItem> list_m;

    public GroceryHandler(Context context) {
        super(context, "temp", null, 1);
        list_m = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<GroceryItem> getAllGroceryTypes(){
        LoadDefaults();
        return list_m;
    }

    public void LoadDefaults() {
        GroceryItem items[] = new GroceryItem[45];
        items[0] = new GroceryItem("Fresh Eggs", 21);
        items[1] = new GroceryItem("Raw Yolks, White", 2);
        items[2] = new GroceryItem("Hard Boiled Eggs", 7);
        items[3] = new GroceryItem("Liquid Eggs or Egg Substitutes, opened",3 );
        items[4] = new GroceryItem("Liquid Eggs or Egg Substitutes, unopened",10 );
        items[5] = new GroceryItem("Egg/chicken/tuna/ham/macaroni salad", 3);
        items[6] = new GroceryItem("Pre-stuffed Meats w/ Dressing", 1);
        items[7] = new GroceryItem("Store-prepared Meals", 3);
        items[8] = new GroceryItem("Vacuum-sealed Dinners with USDA Seal", 14);
        items[9] = new GroceryItem("Ground Meat, Raw", 2);
        items[10] = new GroceryItem("Stew Meat, Raw",2 );
        items[11] = new GroceryItem("Corned Beef, Pickled", 5);
        items[12] = new GroceryItem("Ham, Canned, Unopened", 180);
        items[13] = new GroceryItem("Ham, Canned, Opened", 3);
        items[14] = new GroceryItem("Ham, Cooked, Whole", 7);
        items[15] = new GroceryItem("Ham, Cooked, Half", 3);
        items[16] = new GroceryItem("Ham, Cooked, Slices", 3);
        items[17] = new GroceryItem("Hod Dogs, Unopened", 14);
        items[18] = new GroceryItem("Hot Dogs, Opened", 7);
        items[19] = new GroceryItem("Lunch Meat, Opened", 3);
        items[20] = new GroceryItem("Lunch Meat, Unopened", 14);
        items[21] = new GroceryItem("Soup/Stew", 3);
        items[22] = new GroceryItem("Bacon",7);
        items[23] = new GroceryItem("Sausage, Raw",2);
        items[24] = new GroceryItem("Smoked Sausage Links/Patties",7);
        items[25] = new GroceryItem("Steak, Raw", 3);
        items[26] = new GroceryItem("Pork Chops, Raw", 3);
        items[27] = new GroceryItem("Roast, Raw",3);
        items[28] = new GroceryItem("Variety Meats, Raw",1);
        items[29] = new GroceryItem("Cooked Meat Leftovers",3);
        items[30] = new GroceryItem("Gravy or Meat Broth",2);
        items[31] = new GroceryItem("Chicken/Turkey, Raw",2);
        items[32] = new GroceryItem("Fried Chicken",3);
        items[33] = new GroceryItem("Cooked Chicken",3);
        items[34] = new GroceryItem("Raw Fish", 1);
        items[35] = new GroceryItem("Cooked Fish", 3);
        items[36] = new GroceryItem("Smoked Fish",14);
        items[37] = new GroceryItem("Shrimp/Scallops, Raw",1);
        items[38] = new GroceryItem("Crawfish/Squid, Raw",1);
        items[39] = new GroceryItem("Canned Seafood, opened", 3);
        items[40] = new GroceryItem("Leftover Pizza", 3);
        items[41] = new GroceryItem("Berries", 7);
        items[42] = new GroceryItem("Cooked Vegetables", 3);
        items[43] = new GroceryItem("Leafy Greens", 7);
        items[44] = new GroceryItem("Cooked Leftovers, General", 3);

        for (GroceryItem item : items)
            list_m.add(item);
    }

}
