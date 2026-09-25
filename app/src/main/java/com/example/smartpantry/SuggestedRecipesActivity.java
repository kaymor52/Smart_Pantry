package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuggestedRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_recipes);


        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);

        bottomNavigation.inflateMenu(R.menu.nav_menu);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SuggestedRecipesActivity.this, pantryList.class));
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

    }
}