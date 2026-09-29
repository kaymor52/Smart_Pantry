package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;

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
        public String getName() {
            return name;
        }

        public String getCookTime() {
            return cookTime;
        }

        public String getPrepTime() {
            return prepTime;
        }

        public String getInstructions() {
            return instructions;
        }

        public String getImage() {
            return image;
        }

        public int getId() {
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
        public String getUnits() {
            return units;
        }

        public double getQuantity() {
            return quantity;
        }

        public String getName() {
            return name;
        }

    }

    private spDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_recipes);
        db = new spDatabase(this);

        // RECYCLER VIEW FOR MATCHED =========================================
        RecipeResults_ScreenActivity recipes = db.getRecipesID();

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

        for (RecipeResults_ScreenActivity.AlmostMatch almostMatch : recipes.getAlmostMatching()) {
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

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.inflateMenu(R.menu.nav_menu);
        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SuggestedRecipesActivity.this, PantryHomeActivity.class));
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