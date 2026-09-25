package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class pantryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);

        bottomNavigation.inflateMenu(R.menu.nav_menu);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                return true;
            }

            if (id == R.id.nav_edit) {
                startActivity(new Intent(pantryList.this, PantryManagement.class));
                return true;
            }

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(pantryList.this, SuggestedRecipesActivity.class));
                return true;
            }

            if (id == R.id.nav_settings) {
                startActivity(new Intent(pantryList.this, SettingsActivity.class));
                return true;
            }

            return false;
        });

        bottomNavigation.setSelectedItemId(R.id.nav_pantry);

    }
}