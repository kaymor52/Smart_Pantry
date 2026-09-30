package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spDatabase db = new spDatabase(this);

        ToggleButton toggleAlert = findViewById(R.id.toggleAlert);

//LOAD SAVED SETTING
        if (db.getAlert() == 1) {
            toggleAlert.setChecked(true);
        } else {
            toggleAlert.setChecked(false);
        }
//SAVE SETTING WHENEVER TOGGLE CHANGES
        toggleAlert.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                db.setAlert(1);
            } else {
                db.setAlert(0);
            }

        });
        Button eraseBtn = findViewById(R.id.eraseBtn);
        eraseBtn.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Erase all ingredients?")
                    .setMessage("This will remove all ingredients from your pantry.")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Erase All", (dialog, which) -> {
                        db.deleteAll();
                    })
                    .show();
        });


//BOTTOM NAV BAR
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.inflateMenu(R.menu.nav_menu);
        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(SettingsActivity.this, PantryHomeActivity.class));
                return true;
            }

            if (id == R.id.nav_edit) {
                startActivity(new Intent(SettingsActivity.this, PantryManagement.class));
                return true;
            }

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(SettingsActivity.this, SuggestedRecipesActivity.class));
                return true;
            }

            if (id == R.id.nav_settings) {
                return true;
            }

            return false;
        });

        bottomNavigation.setSelectedItemId(R.id.nav_settings);

    }
}