package com.example.smartpantry.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class spHelper extends SQLiteOpenHelper {

    public spHelper(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {

        super(context, "smartPantry.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE ingredient(" +
                "ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ingredient_name TEXT," +
                "quantity REAL," +
                "unit TEXT," +
                "expiry_date DATE)");

        db.execSQL("CREATE TABLE recipe(" +
                "recipe_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "recipe_name TEXT," +
                "cook_time TIME," +
                "prep_time TIME," +
                "instructions LONGTEXT," +
                "image TEXT)");

        db.execSQL("CREATE TABLE recipe_ingredients(" +
                "recipe_ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ingredient_name TEXT," +
                "ingredient_quantity REAL," +
                "ingredient_unit TEXT," +
                "recipe_id INTEGER," +
                "FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id))");


        // Fettuccine Alfredo
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "0, " +
                "'Fettuccine Alfredo', " +
                "'15 minutes', " +
                "'10 minutes', " +
                "'Cook the fettuccine. Prepare the sauce using butter, heavy cream and Parmesan. Season with salt and black pepper, then combine with the pasta and garnish with parsley.', " +
                "'fettuccine_alfredo')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Fettuccine', 450, 'g', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Heavy Cream', 240, 'ml', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Butter', 4, 'tbsp', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Parmesan', 1, 'cup', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'pinch', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Black Pepper', 1, 'pinch', 0)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Parsley', 1, 'amount', 0)");


        // Beef Mechado
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "1, " +
                "'Beef Mechado', " +
                "'60 minutes', " +
                "'20 minutes', " +
                "'Cook the beef with the listed ingredients until tender and serve with the potatoes.', " +
                "'beef_mechado')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Garlic', 3, 'cloves', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion', 1, 'sliced', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Beef', 907.184, 'g', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Tomato Puree', 8, 'oz', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Water', 240, 'ml', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 45, 'ml', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Lemon', 1, 'slice', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 1, 'large', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Soy Sauce', 60, 'ml', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Black Pepper', 0.5, 'tsp', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Bay Leaves', 2, 'leaves', 1)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'to taste', 1)");


        // Bistek
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "2, " +
                "'Bistek', " +
                "'30 minutes', " +
                "'15 minutes', " +
                "'Cook the beef with soy sauce, lemon, garlic and onions until tender.', " +
                "'bistek')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Beef', 453.592, 'g', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Soy Sauce', 75, 'ml', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Lemon', 1, 'whole', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Garlic', 3, 'cloves', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion', 3, 'parts', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 60, 'ml', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Water', 240, 'ml', 2)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'pinch', 2)");


        // Crispy Eggplant
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "3, " +
                "'Crispy Eggplant', " +
                "'30 minutes', " +
                "'15 minutes', " +
                "'Coat the eggplant in egg, breadcrumbs and sesame seeds, then cook until crispy.', " +
                "'crispy_eggplant')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg Plants', 1, 'large', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Breadcrumbs', 1, 'cup', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sesame Seed', 50, 'g', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Eggs', 2, 'whole', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'to taste', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Pepper', 1, 'to taste', 3)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Vegetable Oil', 1, 'for frying', 3)");


        // Bread Omelette
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "4, " +
                "'Bread Omelette', " +
                "'10 minutes', " +
                "'5 minutes', " +
                "'Make and enjoy.', " +
                "'bread_omelette')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Bread', 2, 'pieces', 4)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg', 2, 'whole', 4)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 0.5, 'amount', 4)");


        // Blini Pancakes
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "5, " +
                "'Blini Pancakes', " +
                "'20 minutes', " +
                "'15 minutes', " +
                "'Prepare the batter using buckwheat, flour, salt, yeast, milk, butter and egg, then cook the pancakes.', " +
                "'blini_pancakes')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Buckwheat', 0.5, 'cup', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Flour', 0.6667, 'cup', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 0.5, 'tsp', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Yeast', 1, 'tsp', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Milk', 240, 'ml', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Butter', 2, 'tbsp', 5)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg', 1, 'whole', 5)");


        // Potato Salad
        db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "6, " +
                "'Potato Salad', " +
                "'30 minutes', " +
                "'20 minutes', " +
                "'Prepare the potatoes, carrots, eggs and other ingredients, then combine with mayonnaise.', " +
                "'potato_salad')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 4, 'pieces', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Carrots', 3, 'pieces', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'tbsp', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('White Wine Vinegar', 0.5, 'tbsp', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Eggs', 4, 'whole', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sausages', 198.45, 'g', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Dill', 113.4, 'g', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Peas', 1, 'can', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onions', 4, 'pieces', 6)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Mayonnaise', 240, 'ml', 6)");


                // Mini Chilli Beef Pies
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "7, " +
                        "'Mini Chilli Beef Pies', " +
                        "'45 minutes', " +
                        "'20 minutes', " +
                        "'Prepare the beef filling with the spices and vegetables, then fill the pastry and bake.', " +
                        "'mini_chilli_beef_pies')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ready rolled shortcrust pastry', 450, 'g', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sunflower Oil', 15, 'ml', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion', 1, 'small', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Hot Chilli Powder', 2, 'tsp', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ground Cumin', 2, 'tsp', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Minced Beef', 250, 'g', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Tomato Puree', 85, 'g', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Beef Stock', 150, 'ml', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ground Cinnamon', 1, 'pinch', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Kidney Beans', 200, 'g', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 1, 'large', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sour Cream', 3, 'tbsp', 7)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Chopped Chive', 2, 'tbsp', 7)");


                // Sticky Chicken
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "8, " +
                        "'Sticky Chicken', " +
                        "'40 minutes', " +
                        "'10 minutes', " +
                        "'Mix the sauce ingredients, coat the chicken drumsticks and cook until done.', " +
                        "'sticky_chicken')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chicken drumsticks', 8, 'pieces', 8)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Soy Sauce', 30, 'ml', 8)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Honey', 15, 'ml', 8)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 15, 'ml', 8)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Tomato Puree', 5, 'ml', 8)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Dijon Mustard', 15, 'ml', 8)");


                // Kenyan Beef Curry
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "9, " +
                        "'Kenyan Beef Curry', " +
                        "'60 minutes', " +
                        "'20 minutes', " +
                        "'Cook the beef with onions, tomatoes, spices and the remaining ingredients until tender.', " +
                        "'kenyan_beef_curry')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Water', 960, 'ml', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sirloin Steak tips', 907.184, 'g', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Garlic Clove', 4, 'cloves', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ground Ginger', 2, 'tsp', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Oil', 30, 'ml', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Red Onions', 2, 'medium', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Tomato', 4, 'pieces', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Paprika', 2, 'tbsp', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Black Pepper', 0.5, 'tsp', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Curry Powder', 2, 'tsp', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Tomato Puree', 4, 'tbsp', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'dash', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chilli', 1, 'piece', 9)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Cilantro leaves', 1, 'amount', 9)");


                // Sukuma Wiki
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "10, " +
                        "'Sukuma Wiki', " +
                        "'25 minutes', " +
                        "'10 minutes', " +
                        "'Cook the onions and kale with oil, salt and cream until tender.', " +
                        "'sukuma_wiki')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Oil', 30, 'ml', 10)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Red Onions', 2, 'medium', 10)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Kale', 453.592, 'g', 10)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'dash', 10)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Double Cream', 240, 'ml', 10)");


                // Cinnamon Buns
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "11, " +
                        "'Cinnamon Buns', " +
                        "'25 minutes', " +
                        "'30 minutes', " +
                        "'Prepare the dough, add the cinnamon filling, shape the buns and bake until golden.', " +
                        "'cinnamon_buns')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Unsalted Butter', 175, 'g', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Milk', 200, 'ml', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'tsp', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Flour', 250, 'g', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Strong White Flour', 250, 'g', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Fast action yeast', 1.5, 'tsp', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Cardamom', 1, 'tsp', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Caster Sugar', 101, 'g', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg', 3, 'whole', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 1, 'dash', 11)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Cinnamon', 2, 'tbsp', 11)");


                // Karbonader
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "12, " +
                        "'Karbonader', " +
                        "'30 minutes', " +
                        "'15 minutes', " +
                        "'Combine the beef with seasoning and other ingredients, form patties and cook until done. Serve with caramelized onions.', " +
                        "'karbonader')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion', 2, 'pieces', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Butter', 1, 'tbsp', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ground Beef', 453.592, 'g', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 0.5, 'tsp', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Pepper', 0.5, 'tsp', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Nutmeg', 0.5, 'tsp', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Cornstarch', 0.5, 'tbsp', 12)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Water', 50, 'ml', 12)");


                // Jamon & Wild Garlic Croquetas
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "13, " +
                        "'Jamon & Wild Garlic Croquetas', " +
                        "'40 minutes', " +
                        "'30 minutes', " +
                        "'Prepare the croqueta mixture, shape into portions, coat with egg and breadcrumbs, then cook.', " +
                        "'jamon_wild_garlic_croquetas')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Wild Garlic Leaves', 150, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Milk', 350, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 45, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Flour', 65, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('manchego', 35, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('jamón ibérico', 80, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg', 1, 'whole', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Breadcrumbs', 75, 'g', 13)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Vegetable Oil', 1000, 'ml', 13)");


                // Churros
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "14, " +
                        "'Churros', " +
                        "'30 minutes', " +
                        "'20 minutes', " +
                        "'Prepare the churro mixture and chocolate accompaniment using the listed ingredients.', " +
                        "'churros')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Butter', 50, 'g', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Vanilla Extract', 0.5, 'tsp', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Plain Flour', 250, 'g', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Baking Powder', 1, 'tsp', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sunflower Oil', 1000, 'ml', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Bread', 2, 'pieces', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Dark Chocolate', 200, 'g', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Double Cream', 100, 'ml', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Milk', 100, 'ml', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Golden Syrup', 45, 'ml', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Caster Sugar', 100, 'g', 14)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Cinnamon', 10, 'ml', 14)");


                // Chorizo, Potato & Cheese Omelette
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "15, " +
                        "'Chorizo, Potato & Cheese Omelette', " +
                        "'25 minutes', " +
                        "'10 minutes', " +
                        "'Cook the potato and chorizo, add the eggs and cheese, then cook until the omelette is set.', " +
                        "'chorizo_potato_cheese_omelette')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 1, 'small', 15)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Olive Oil', 5, 'ml', 15)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chorizo', 50, 'g', 15)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg', 3, 'whole', 15)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Parsley', 1, 'chopped', 15)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                        "('Cheddar Cheese', 25, 'g', 15)");


                // Kentucky Fried Chicken
                db.execSQL("INSERT INTO recipe(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                        "16, " +
                        "'Kentucky Fried Chicken', " +
                        "'45 minutes', " +
                        "'20 minutes', " +
                        "'Prepare the chicken with the listed coating ingredients and cook according to the recipe.', " +
                        "'kentucky_fried_chicken')");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chicken', 1, 'whole', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Oil', 1892.706, 'ml', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg White', 1, 'whole', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Flour', 1.5, 'cups', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Brown Sugar', 1, 'tbsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Salt', 1, 'tbsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Paprika', 1, 'tbsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion Salt', 2, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chili Powder', 1, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Black Pepper', 1, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Celery Salt', 0.5, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Sage', 0.5, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Garlic Powder', 0.5, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Allspice', 0.5, 'tsp', 16)");

        db.execSQL("INSERT INTO recipe_ingredients(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Oregano', 0.5, 'tsp', 16)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ingredient");
        db.execSQL("DROP TABLE IF EXISTS recipe_ingredients");
        db.execSQL("DROP TABLE IF EXISTS recipe");

        onCreate(db);
    }
}