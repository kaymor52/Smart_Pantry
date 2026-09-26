package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class PantryHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spDatabase db = new spDatabase(this);


        setContentView(R.layout.activity_pantry_home);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);

        bottomNavigation.inflateMenu(R.menu.nav_menu);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                return true;
            }

            if (id == R.id.nav_edit) {
                startActivity(new Intent(PantryHome.this, PantryManagement.class));
                return true;
            }

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(PantryHome.this, SuggestedRecipesActivity.class));
                return true;
            }

            if (id == R.id.nav_settings) {
                startActivity(new Intent(PantryHome.this, SettingsActivity.class));
                return true;
            }

            return false;
        });

        bottomNavigation.setSelectedItemId(R.id.nav_pantry);


        RecyclerView recyclerView = findViewById(R.id.pantry_recyclerView_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PantryManagement.Ingredient> ingredients = db.showIngredients();

        Pantry_list_adapter adapter = new Pantry_list_adapter(ingredients);

        recyclerView.setAdapter(adapter);
    }
}