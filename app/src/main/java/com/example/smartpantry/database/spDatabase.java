package com.example.smartpantry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartpantry.RecipeResults;
import com.example.smartpantry.RecipeResults.*;
import com.example.smartpantry.PantryManagement.Ingredient;
import com.example.smartpantry.SuggestedRecipesActivity;
import com.example.smartpantry.SuggestedRecipesActivity.Recipe;

import java.util.ArrayList;


public class spDatabase {

    private final spHelper dbHelper;

    public spDatabase(Context context) {
        dbHelper = new spHelper(context, null, null, 1);
    }

    public void addIngredient(String name, int quantity, String unit, String expire) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ingredient_name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiry_date", expire);

        db.insert("ingredient", null, values);

        db.close();
    }

    public RecipeResults getRecipesID() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor recipe = db.query(
                "recipe",
                null,
                null,
                null,
                null,
                null,
                null
        );

        Cursor rIngredient = db.query(
                "recipe_ingredients",
                null,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Ingredient> ingredients = showIngredients();

        ArrayList<Integer> matching = new ArrayList<>();
        ArrayList<AlmostMatch> almostMatching = new ArrayList<>();

        while (recipe.moveToNext()) {

            // RESET RECIPE INGREDIENT CURSOR
            rIngredient.moveToPosition(-1);

            // GET RECIPE ID
            int recipeId = recipe.getInt(
                    recipe.getColumnIndexOrThrow("recipe_id")
            );

            int itemCount = 0;
            int requiredItemCount = 0;
            int missingId = 0;

            while (rIngredient.moveToNext()) {

                // GET RECIPE INGREDIENT ID
                int ingredientRecipeId = rIngredient.getInt(
                        rIngredient.getColumnIndexOrThrow("recipe_id")
                );

                // ONLY CHECK INGREDIENTS BELONGING TO THIS RECIPE
                if (ingredientRecipeId == recipeId) {

                    requiredItemCount++;

                    String requiredIngredient = rIngredient.getString(
                            rIngredient.getColumnIndexOrThrow("ingredient_name")
                    );

                    int requiredQuantity = rIngredient.getInt(
                            rIngredient.getColumnIndexOrThrow("ingredient_quantity")
                    );

                    boolean ingredientFound = false;

                    // SEARCH PANTRY
                    for (Ingredient ingredient : ingredients) {

                        String pantryName = ingredient.getName();
                        int pantryQuantity = ingredient.getQuantity();

                        if (wordFilter(pantryName).contains(
                                wordFilter(requiredIngredient))) {

                            if (pantryQuantity >= requiredQuantity) {

                                ingredientFound = true;
                                break;

                            }
                        }
                    }

                    // REQUIRED INGREDIENT WAS NOT FOUND
                    if (!ingredientFound) {

                        itemCount++;

                        missingId = rIngredient.getInt(
                                rIngredient.getColumnIndexOrThrow("recipe_ingredient_id")
                        );
                    }
                }
            }

            // ALL INGREDIENTS AVAILABLE
            if (itemCount == 0 && requiredItemCount > 0) {

                matching.add(recipeId);

                // EXACTLY ONE INGREDIENT MISSING
            } else if (itemCount == 1) {

                almostMatching.add(
                        new AlmostMatch(recipeId, missingId)
                );
            }
        }

        rIngredient.close();
        recipe.close();

        return new RecipeResults(matching, almostMatching);
    }
    public String wordFilter(String word){

      word = word.toLowerCase().trim();
       if(word.endsWith("ies")){
          word = word.substring(0, word.length() - 3);
       }else if(word.endsWith("es")){
           word =  word.substring(0, word.length() - 2);
       }else if(word.endsWith("s")){
           word =  word.substring(0, word.length() - 1);
       }
        return word;
    }

    public ArrayList<Ingredient> showIngredients() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] contentFormat = {"ingredient_id", "ingredient_name", "quantity", "unit", "expiry_date"};
        Cursor c = db.query("ingredient", contentFormat, null, null, null, null, "ingredient_name");


        ArrayList<Ingredient> ingredientList = new ArrayList<>(); //STORING INGREDIENTS

        while (c.moveToNext()) {
            Ingredient ingredient = new Ingredient(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4));
            ingredientList.add(ingredient);
        }
        c.close();
        return ingredientList;
    }

    public ArrayList<Recipe> showRecipesByIds(ArrayList<Integer> recipeIds) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Recipe> recipeList = new ArrayList<>();

        for (int id : recipeIds) {

            Cursor c = db.query(
                    "recipe",
                    null,
                    "recipe_id = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );

            if (c.moveToFirst()) {

                Recipe recipe = new Recipe(
                        c.getInt(c.getColumnIndexOrThrow("recipe_id")),
                        c.getString(c.getColumnIndexOrThrow("recipe_name")),
                        c.getString(c.getColumnIndexOrThrow("cook_time")),
                        c.getString(c.getColumnIndexOrThrow("prep_time")),
                        c.getString(c.getColumnIndexOrThrow("instructions")),
                        c.getString(c.getColumnIndexOrThrow("image"))
                );

                recipeList.add(recipe);
            }

            c.close();
        }

        return recipeList;
    }
    public ArrayList<SuggestedRecipesActivity.RecipeIngredients> showRecipeIngredients(int id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] contentFormat = {
                "ingredient_name",
                "ingredient_quantity",
                "ingredient_unit"
        };

        Cursor c = db.query(
                "recipe_ingredients",
                contentFormat,
                "recipe_id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        ArrayList<SuggestedRecipesActivity.RecipeIngredients> recipeList = new ArrayList<>();

        while (c.moveToNext()) {

            SuggestedRecipesActivity.RecipeIngredients ingredient =
                    new SuggestedRecipesActivity.RecipeIngredients(
                            c.getString(0),
                            c.getDouble(1),
                            c.getString(2)
                    );

            recipeList.add(ingredient);
        }

        c.close();
        return recipeList;
    }
    public void deleteIngredient(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("ingredient", "ingredient_id = ?", new String[]{String.valueOf(id)});
    }

    public void updateIngredient(int id, String name, int quantity, String unit, String expiryDate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ingredient_name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiry_date", expiryDate);

        db.update("ingredient", values, "ingredient_id = ?", new String[]{String.valueOf(id)});
    }
}