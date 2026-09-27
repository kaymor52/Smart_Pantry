package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import com.example.smartpantry.database.spDatabase;

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
    }

    private spDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_recipes);
        db = new spDatabase(this);

        RecipeResults recipes = db.getRecipesID();

        TextView matching = findViewById(R.id.resultEditText);
        TextView almostMatch = findViewById(R.id.almostResults);

        Button show = findViewById(R.id.showRecipe);

        show.setOnClickListener(v -> {
                    ArrayList<Recipe> matchingRecipes = db.showRecipes();
                    Recipe recipe = matchingRecipes.get(0);
                    matching.setText(recipe.name +
                            "\nPreparation time: " + recipe.prepTime +
                            "\nCook Time: " + recipe.cookTime +
                            "\nInstuctions \n\n" + recipe.instructions);
                });

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