package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.adapters.RecipeResultAdapter;
import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {

    public static class Recipe {

        private int id;
        private String name;
        private String cookTime;
        private String prepTime;
        private String instructions;
        private String image;

        public Recipe(int id, String name, String cookTime, String prepTime,
                      String instructions, String image) {

            this.id = id;
            this.name = name;
            this.cookTime = cookTime;
            this.prepTime = prepTime;
            this.instructions = instructions;
            this.image = image;
        }
        public String getImage(){
            return image;
        }
        public String getName(){
            return name;
        }
        public int getId(){
            return id;
        }
    }

    public static class RecipeIngredients {

        private String units;
        private double quantity;
        private String name;

        public RecipeIngredients(String units, double quantity, String name) {
            this.name = name;
            this.units = units;
            this.quantity = quantity;
        }

    }

    private spDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_recipes);
        db = new spDatabase(this);

        // RECYCLER VIEW FOR MATCHED =========================================
        RecipeResults recipes = db.getRecipesID();

        RecyclerView recyclerView = findViewById(R.id.AvailableView);

        ArrayList<Recipe> matchingRecipes =
                db.showRecipesByIds(recipes.getMatching());

        RecipeResultAdapter adapter =
                new RecipeResultAdapter(matchingRecipes);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(adapter);

        // RECYCLER VIEW FOR ALMOST MATCH =========================================
        RecyclerView almostRecycler = findViewById(R.id.almostRecycler);

        ArrayList<Integer> almostRecipeIds = new ArrayList<>();

        for (RecipeResults.AlmostMatch almostMatch : recipes.getAlmostMatching()) {
            almostRecipeIds.add(almostMatch.getRecipeId());
        }

        ArrayList<Recipe> almostRecipes =
                db.showRecipesByIds(almostRecipeIds);

        RecipeResultAdapter almostAdapter =
                new RecipeResultAdapter(almostRecipes);

        almostRecycler.setLayoutManager(
                new LinearLayoutManager(this)
        );

        almostRecycler.setAdapter(almostAdapter);

//        TextView matching = findViewById(R.id.resultEditText);
//        TextView almostMatch = findViewById(R.id.almostResults);






//            todo============= >use for detailed screen
//            ArrayList<Recipe> matchingRecipes = db.showRecipesByIds(recipes.getMatching());

//            String ingredientList="";
//            for(Recipe recipe : matchingRecipes) {
//
//                ArrayList<RecipeIngredients> rIngredients = db.showRecipeIngredients(recipe.id);
//
//            }
//            matching.setText(recipe.name +
//                    "\nPreparation time: " + recipe.prepTime +
//                    "\nCook Time: " + recipe.cookTime +
//                    "\nIngredients: " +//todo===========>
//                    "\nInstuctions \n\n" + recipe.instructions);
//
//            ImageView icon = findViewById(R.id.imageView);
//
//            String image1 = recipe.image;
//
//            int imageId = getResources().getIdentifier(
//                    image1,
//                    "drawable",
//                    getPackageName()
//            );
//
//            icon.setImageResource(imageId);


        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.inflateMenu(R.menu.nav_menu);
        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SuggestedRecipesActivity.this, PantryHome.class));
                return true;
            }

            if (id == R.id.nav_edit) {
                startActivity(new Intent(SuggestedRecipesActivity.this, PantryManagement.class));
                return true;
            }

            if (id == R.id.nav_recipes) {
                return true;
            }

            if (id == R.id.nav_settings) {
                startActivity(new Intent(SuggestedRecipesActivity.this, SettingsActivity.class));
                return true;
            }

            return false;
        });
        bottomNavigation.setSelectedItemId(R.id.nav_recipes);
    }
}