package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.PantryManagement.Ingredient;
import com.example.smartpantry.adapters.PantryListAdapter;
import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PantryHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spDatabase db = new spDatabase(this);
        setContentView(R.layout.activity_pantry_home);

//region RETRIEVE NOTIFICATIONS =======================================
        ArrayList<Ingredient> ingredientList = db.showIngredients();
        ArrayList<Ingredient> expiringItems = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        Date today = new Date();

        for (Ingredient ingredient : ingredientList) {

            if (ingredient.getExpiry() == null ||
                    ingredient.getExpiry().trim().isEmpty()) {
                continue;
            }

            try {

                Date ingredientDate =
                        dateFormat.parse(ingredient.getExpiry());

                long difference =
                        ingredientDate.getTime() - today.getTime();

                long daysLeft =
                        difference / (1000 * 60 * 60 * 24);

                if (daysLeft <= 7 && daysLeft >= 0) {
                    expiringItems.add(ingredient);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

// Create notification message
        String message = "";

        for (Ingredient ingredient : expiringItems) {

            try {

                Date ingredientDate =
                        dateFormat.parse(ingredient.getExpiry());

                long difference =
                        ingredientDate.getTime() - today.getTime();

                long daysLeft =
                        difference / (1000 * 60 * 60 * 24);

                message += String.format(
                        "%-20s [%d days left]\n",
                        ingredient.getName(),
                        daysLeft
                );

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

// Notification button
        ImageButton notificationButton =
                findViewById(R.id.notificationButton);

// Check if alerts are enabled
        if (db.getAlert() == 1) {

            notificationButton.setVisibility(View.VISIBLE);

            String finalMessage = message;
            notificationButton.setOnClickListener(v -> {

                String notificationMessage;

                if (expiringItems.isEmpty()) {

                    notificationMessage =
                            "No ingredients are expiring within 7 days.";

                } else {

                    notificationMessage = finalMessage;

                }

                new AlertDialog.Builder(this)
                        .setTitle("Expiring Soon")
                        .setMessage(notificationMessage)
                        .setPositiveButton("OK", null)
                        .show();
            });

        } else {

            notificationButton.setVisibility(View.GONE);
        }
//endregion


//      NAVIGATION BAR ======================================================
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.inflateMenu(R.menu.nav_menu);
        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                return true;
            }

            if (id == R.id.nav_edit) {
                startActivity(new Intent(PantryHomeActivity.this, PantryManagement.class));
                return true;
            }

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(PantryHomeActivity.this, SuggestedRecipesActivity.class));
                return true;
            }

            if (id == R.id.nav_settings) {
                startActivity(new Intent(PantryHomeActivity.this, SettingsActivity.class));
                return true;
            }

            return false;
        });
        bottomNavigation.setSelectedItemId(R.id.nav_pantry);

        RecyclerView recyclerView = findViewById(R.id.pantry_recyclerView_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PantryManagement.Ingredient> ingredients = db.showIngredients();

        PantryListAdapter adapter = new PantryListAdapter(ingredients);

        recyclerView.setAdapter(adapter);

        // SHOW MESSAGE IF NO RECIPES ARE AVAILABLE
        TextView noAvailableText =
                findViewById(R.id.noAvailableText);

        if (ingredients.isEmpty()) {

            recyclerView.setVisibility(View.GONE);
            noAvailableText.setVisibility(View.VISIBLE);

        } else {

            recyclerView.setVisibility(View.VISIBLE);
            noAvailableText.setVisibility(View.GONE);

        }
    }
}