package com.example.smartpantry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.smartpantry.PantryManagement.Ingredient;

import java.util.ArrayList;


public class spDatabase {

    private final spHelper dbHelper;

    public spDatabase(Context context) {
        dbHelper = new spHelper(context, null, null, 1);
    }

    public void addIngredient(String name, int quantity, String unit) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ingredient_name",name);
        values.put("quantity", quantity);
        values.put("unit", unit);

        db.insert("ingredient", null, values);

        db.close();
    }
    public ArrayList<Ingredient> showIngredients(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] contentFormat ={"ingredient_id","ingredient_name","quantity","unit"};
        Cursor c = db.query("ingredient",contentFormat,null,null,null,null,"ingredient_name");


        ArrayList<Ingredient> ingredientList = new ArrayList<>(); //STORING INGREDIENTS

        while (c.moveToNext()) {
            Ingredient ingredient = new Ingredient(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
            );

            ingredientList.add(ingredient);


        }
        c.close();
         return ingredientList;
    }

    public void deleteIngredient(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(
                "ingredient",
                "ingredient_id = ?",
                new String[]{String.valueOf(id)}
        );
    }
    public void updateIngredient(int id, String name, int quantity, String unit, String expiryDate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ingredient_name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiry_date",expiryDate);

        db.update(
                "ingredient",
                values,
                "ingredient_id = ?",
                new String[]{String.valueOf(id)}
        );
    }
}