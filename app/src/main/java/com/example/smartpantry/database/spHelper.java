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

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "0, 'Fettuccine Alfredo', 30, 5, " +
                "'Step 1: Cook pasta according to package instructions in a large pot of boiling water and salt.\n" +
                "Step 2: Add heavy cream and butter to a large skillet over medium heat until the cream bubbles and the butter melts.\n" +
                "Step 3: Whisk in parmesan and add seasoning (salt and black pepper).\n" +
                "Step 4: Let the sauce thicken slightly and then add the pasta and toss until coated in sauce.\n" +
                "Step 5: Garnish with parsley, and it''s ready.', " +
                "'fettuccine_alfredo')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Fettuccine', 450, 'g', 0)," +
                "('Heavy Cream', 1, 'cup', 0)," +
                "('Butter', 4, 'tbsp', 0)," +
                "('Parmesan', 1, 'cup', 0)," +
                "('Salt', 1, 'pinch', 0)," +
                "('Black Pepper', 1, 'pinch', 0)," +
                "('Parsley', 1, 'amount', 0)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "1, 'Beef Mechado', 60, 15, " +
                "'Cook the beef with the garlic, onion, tomato puree, water, olive oil, lemon, potatoes, soy sauce, black pepper, bay leaves and salt until tender.', " +
                "'beef_mechado')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Garlic', 3, 'cloves', 1)," +
                "('Onion', 1, 'sliced', 1)," +
                "('Beef', 2, 'lbs', 1)," +
                "('Tomato Puree', 8, 'oz', 1)," +
                "('Water', 1, 'cup', 1)," +
                "('Olive Oil', 3, 'tbsp', 1)," +
                "('Lemon', 1, 'slice', 1)," +
                "('Potatoes', 1, 'large', 1)," +
                "('Soy Sauce', 0.25, 'cup', 1)," +
                "('Black Pepper', 0.5, 'tsp', 1)," +
                "('Bay Leaves', 2, 'leaves', 1)," +
                "('Salt', 1, 'to taste', 1)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "2, 'Bistek', 30, 10, " +
                "'Marinate the beef with soy sauce and lemon. Cook the beef with garlic, onion, olive oil, water and salt until done.', " +
                "'bistek')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Beef', 1, 'lb', 2)," +
                "('Soy Sauce', 5, 'tbsp', 2)," +
                "('Lemon', 1, 'whole', 2)," +
                "('Garlic', 3, 'cloves', 2)," +
                "('Onion', 3, 'parts', 2)," +
                "('Olive Oil', 4, 'tbsp', 2)," +
                "('Water', 1, 'cup', 2)," +
                "('Salt', 1, 'pinch', 2)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "3, 'Crispy Eggplant', 20, 10, " +
                "'Coat the eggplant in egg and breadcrumbs mixed with sesame seeds and seasoning. Cook until crispy.', " +
                "'crispy_eggplant')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Egg Plants', 1, 'large', 3)," +
                "('Breadcrumbs', 1, 'cup', 3)," +
                "('Sesame Seed', 50, 'g', 3)," +
                "('Eggs', 2, 'whole', 3)," +
                "('Salt', 1, 'to taste', 3)," +
                "('Pepper', 1, 'to taste', 3)," +
                "('Vegetable Oil', 1, 'for frying', 3)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "4, 'Bread omelette', 10, 5, " +
                "'Make the omelette with the bread and eggs, season with salt and enjoy.', " +
                "'bread_omelette')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Bread', 2, 'pieces', 4)," +
                "('Egg', 2, 'whole', 4)," +
                "('Salt', 0.5, 'amount', 4)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "5, 'Blini Pancakes', 20, 15, " +
                "'Combine the buckwheat, flour, salt, yeast and milk. Add butter and separated egg, then cook the pancakes until done.', " +
                "'blini_pancakes')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Buckwheat', 0.5, 'cup', 5)," +
                "('Flour', 0.67, 'cup', 5)," +
                "('Salt', 0.5, 'tsp', 5)," +
                "('Yeast', 1, 'tsp', 5)," +
                "('Milk', 1, 'cup', 5)," +
                "('Butter', 2, 'tbsp', 5)," +
                "('Egg', 1, 'whole', 5)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "6, 'Potato Salad (Olivier Salad)', 30, 20, " +
                "'Cook the potatoes, carrots and eggs. Combine with sausages, dill, peas, onions, vinegar, salt and mayonnaise.', " +
                "'potato_salad')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 4, 'pieces', 6)," +
                "('Carrots', 3, 'pieces', 6)," +
                "('Salt', 1, 'tbsp', 6)," +
                "('White Wine Vinegar', 0.5, 'tbsp', 6)," +
                "('Eggs', 4, 'whole', 6)," +
                "('Sausages', 7, 'oz', 6)," +
                "('Dill', 4, 'oz', 6)," +
                "('Peas', 1, 'can', 6)," +
                "('Onions', 4, 'pieces', 6)," +
                "('Mayonnaise', 1, 'cup', 6)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "7, 'Mini chilli beef pies', 45, 20, " +
                "'Prepare the beef filling with onion, chilli powder, cumin, minced beef, tomato puree, beef stock, cinnamon, kidney beans and potatoes. Fill pastry and bake until cooked.', " +
                "'mini_chilli_beef_pies')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Ready rolled shortcrust pastry', 450, 'g', 7)," +
                "('Sunflower Oil', 1, 'tbsp', 7)," +
                "('Onion', 1, 'small', 7)," +
                "('Hot Chilli Powder', 2, 'tsp', 7)," +
                "('Ground Cumin', 2, 'tsp', 7)," +
                "('Minced Beef', 250, 'g', 7)," +
                "('Tomato Puree', 85, 'g', 7)," +
                "('Beef Stock', 150, 'ml', 7)," +
                "('Ground Cinnamon', 1, 'pinch', 7)," +
                "('Kidney Beans', 200, 'g', 7)," +
                "('Potatoes', 1, 'large', 7)," +
                "('Sour Cream', 3, 'tbsp', 7)," +
                "('Chopped Chive', 2, 'tbsp', 7)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "8, 'Sticky Chicken', 40, 10, " +
                "'Combine soy sauce, honey, olive oil, tomato puree and Dijon mustard. Coat the chicken drumsticks and cook until done.', " +
                "'sticky_chicken')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chicken drumsticks', 8, 'pieces', 8)," +
                "('Soy Sauce', 2, 'tbsp', 8)," +
                "('Honey', 1, 'tbsp', 8)," +
                "('Olive Oil', 1, 'tbsp', 8)," +
                "('Tomato Puree', 1, 'tsp', 8)," +
                "('Dijon Mustard', 1, 'tbsp', 8)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "9, 'Kenyan Beef Curry', 60, 20, " +
                "'Cook the beef with garlic, ginger, oil, onions, tomatoes, paprika, black pepper, curry powder, tomato puree, salt, chilli and cilantro.', " +
                "'kenyan_beef_curry')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Water', 4, 'cups', 9)," +
                "('Sirloin Steak tips', 2, 'lbs', 9)," +
                "('Garlic Clove', 4, 'cloves', 9)," +
                "('Ground Ginger', 2, 'tsp', 9)," +
                "('Oil', 2, 'tbsp', 9)," +
                "('Red Onions', 2, 'medium', 9)," +
                "('Tomato', 4, 'pieces', 9)," +
                "('Paprika', 2, 'tbsp', 9)," +
                "('Black Pepper', 0.5, 'tsp', 9)," +
                "('Curry Powder', 2, 'tsp', 9)," +
                "('Tomato Puree', 4, 'tbsp', 9)," +
                "('Salt', 1, 'dash', 9)," +
                "('Chilli', 1, 'piece', 9)," +
                "('Cilantro leaves', 1, 'amount', 9)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "10, 'Sukuma Wiki', 20, 10, " +
                "'Cook the onions in oil, add kale and seasoning, then add double cream and cook until ready.', " +
                "'sukuma_wiki')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Oil', 2, 'tbsp', 10)," +
                "('Red Onions', 2, 'medium', 10)," +
                "('Kale', 1, 'lb', 10)," +
                "('Salt', 1, 'dash', 10)," +
                "('Double Cream', 1, 'cup', 10)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "11, 'Cinnamon buns', 25, 30, " +
                "'Prepare the dough using butter, milk, salt, flour, yeast, cardamom, sugar and eggs. Prepare the filling with butter, sugar and cinnamon, then shape and bake the buns.', " +
                "'cinnamon_buns')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Unsalted Butter', 175, 'g', 11)," +
                "('Milk', 200, 'ml', 11)," +
                "('Salt', 1, 'tsp', 11)," +
                "('Flour', 250, 'g', 11)," +
                "('Strong White Flour', 250, 'g', 11)," +
                "('Fast action yeast', 1.5, 'tsp', 11)," +
                "('Cardamom', 1, 'tsp', 11)," +
                "('Caster Sugar', 101, 'g', 11)," +
                "('Egg', 3, 'whole', 11)," +
                "('Olive Oil', 1, 'dash', 11)," +
                "('Cinnamon', 2, 'tbsp', 11)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "12, 'Karbonader (Lean Beef Patties) with Caramelized Onions', 30, 15, " +
                "'Cook the onions with butter until caramelized. Mix ground beef with salt, pepper, nutmeg, cornstarch and water. Shape into patties and cook.', " +
                "'karbonader')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Onion', 2, 'pieces', 12)," +
                "('Butter', 3, 'tbsp', 12)," +
                "('Ground Beef', 1, 'lb', 12)," +
                "('Salt', 0.5, 'tsp', 12)," +
                "('Pepper', 0.5, 'tsp', 12)," +
                "('Nutmeg', 0.5, 'tsp', 12)," +
                "('Cornstarch', 0.5, 'tbsp', 12)," +
                "('Water', 50, 'ml', 12)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "13, 'Jamon & wild garlic croquetas', 30, 30, " +
                "'Prepare a mixture using wild garlic, milk, olive oil, flour, manchego and jamón ibérico. Shape into croquetas, coat with egg and breadcrumbs, then cook until golden.', " +
                "'jamon_wild_garlic_croquetas')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Wild Garlic Leaves', 150, 'g', 13)," +
                "('Milk', 350, 'g', 13)," +
                "('Olive Oil', 45, 'g', 13)," +
                "('Flour', 65, 'g', 13)," +
                "('manchego', 35, 'g', 13)," +
                "('jamón ibérico', 80, 'g', 13)," +
                "('Egg', 1, 'whole', 13)," +
                "('Breadcrumbs', 75, 'g', 13)," +
                "('Vegetable Oil', 1, 'L', 13)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "14, 'Churros', 30, 20, " +
                "'Prepare the churro mixture using butter, vanilla, flour and baking powder. Serve with a chocolate sauce made using dark chocolate, cream, milk and golden syrup. Finish with caster sugar and cinnamon.', " +
                "'churros')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Butter', 50, 'g', 14)," +
                "('Vanilla Extract', 0.5, 'tsp', 14)," +
                "('Plain Flour', 250, 'g', 14)," +
                "('Baking Powder', 1, 'tsp', 14)," +
                "('Sunflower Oil', 1, 'L', 14)," +
                "('Bread', 2, 'pieces', 14)," +
                "('Dark Chocolate', 200, 'g', 14)," +
                "('Double Cream', 100, 'ml', 14)," +
                "('Milk', 100, 'ml', 14)," +
                "('Golden Syrup', 3, 'tbsp', 14)," +
                "('Caster Sugar', 100, 'g', 14)," +
                "('Cinnamon', 2, 'tsp', 14)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "15, 'Chorizo, potato & cheese omelette', 20, 10, " +
                "'Cook the potato and chorizo, add beaten eggs, parsley and cheddar cheese, then cook until the omelette is set.', " +
                "'chorizo_potato_cheese_omelette')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Potatoes', 1, 'small', 15)," +
                "('Olive Oil', 1, 'tsp', 15)," +
                "('Chorizo', 50, 'g', 15)," +
                "('Egg', 3, 'whole', 15)," +
                "('Parsley', 1, 'chopped', 15)," +
                "('Cheddar Cheese', 25, 'g', 15)");

        db.execSQL("INSERT INTO recipe " +
                "(recipe_id, recipe_name, cook_time, prep_time, instructions, image) VALUES(" +
                "16, 'Kentucky Fried Chicken', 40, 20, " +
                "'Prepare the chicken and coat it with the seasoned flour mixture. Cook until fully done and serve.', " +
                "'kentucky_fried_chicken')");

        db.execSQL("INSERT INTO recipe_ingredients " +
                "(ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES" +
                "('Chicken', 1, 'whole', 16)," +
                "('Oil', 2, 'quarts', 16)," +
                "('Egg White', 1, 'whole', 16)," +
                "('Flour', 1.5, 'cups', 16)," +
                "('Brown Sugar', 1, 'tbsp', 16)," +
                "('Salt', 1, 'tbsp', 16)," +
                "('Paprika', 1, 'tbsp', 16)," +
                "('Onion Salt', 2, 'tsp', 16)," +
                "('Chili Powder', 1, 'tsp', 16)," +
                "('Black Pepper', 1, 'tsp', 16)," +
                "('Celery Salt', 0.5, 'tsp', 16)," +
                "('Sage', 0.5, 'tsp', 16)," +
                "('Garlic Powder', 0.5, 'tsp', 16)," +
                "('Allspice', 0.5, 'tsp', 16)," +
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