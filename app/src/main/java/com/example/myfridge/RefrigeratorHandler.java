package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefrigeratorHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Refrigerator";
    private static final String TABLE_CONTENTS = "RefrigeratorContents";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_PURCHASED = "purchased";
    private static final String KEY_EXPIRES = "expires";

    public RefrigeratorHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTENTS + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_TYPE + " TEXT,"
                + KEY_PURCHASED + " TEXT," + KEY_EXPIRES + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENTS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void Add_Item(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        Date purchased = item.Get_Date();
        Date expires = item.Get_Expiration();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.Get_Name());
        values.put(KEY_TYPE, item.Get_Type());
        values.put(KEY_PURCHASED, purchased.toString());
        values.put(KEY_EXPIRES, expires.toString());

        db.beginTransaction();

        // Inserting Row
        db.insert(TABLE_CONTENTS, null, values);
        //2nd argument is String containing nullColumnHack
        //db.close(); // Closing database connection

        db.endTransaction();
    }

    Item Get_Item(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTENTS, new String[] { KEY_ID, KEY_NAME,
                        KEY_TYPE, KEY_PURCHASED, KEY_EXPIRES }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Date entry_date = new Date(cursor.getString(3));
        Date entry_expire = new Date(cursor.getString(4));

        Item item = new Item(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                entry_date,
                entry_expire
        );
        return item;
    }

    public List<Item> getAllContacts() {
        List<Item> contactList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                Date date = new Date(cursor.getString(3));
                Date expires = new Date(cursor.getString(4));

                Item item = new Item(id, name, type, date, expires);

                // Adding contact to list
                contactList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.Get_Name());
        values.put(KEY_TYPE, item.Get_Type());
        values.put(KEY_PURCHASED, item.Get_Date().toString());
        values.put(KEY_EXPIRES, item.Get_Expiration().toString());

        // updating row
        return db.update(TABLE_CONTENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.Get_ID()) });
    }

    // Deleting single contact
    public void deleteItem(Item name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        db.delete(TABLE_CONTENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(name.Get_ID()) });
        db.endTransaction();
    }

    // Getting contacts Count
    public int getItemsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }
}
