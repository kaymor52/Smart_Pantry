package com.example.smartpantry.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class spHelper extends SQLiteOpenHelper {
    public spHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "smartPantry.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ingredient(" +
                "ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ingredient_name text," +
                "quantity INTEGER," +
                "unit text," +
                "expiry_date DATE)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ingredient");
        onCreate(db);
    }
}
