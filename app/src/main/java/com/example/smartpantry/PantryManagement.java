package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.adapters.ingredientAdapter;
import com.example.smartpantry.database.spDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PantryManagement extends AppCompatActivity {
    private spDatabase db;
    ingredientAdapter adapter;

    public static class Ingredient {
        private int id;
        private final String name;
        private final int quantity;
        private final String unit;
        private final String expiry;

        public Ingredient(String name, int quantity, String unit, String expiry) {

            this.name = name;
            this.quantity = quantity;
            this.unit = unit;
            this.expiry = expiry;
        }

        public Ingredient(Integer id, String name, int quantity, String unit, String expiry) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.unit = unit;
            this.expiry = expiry;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getUnit() {
            return unit;
        }

        public String getExpiry() {
            return expiry;
        }


    }

    //REFRESH AFTER CLOSING EDIT WINDOW
    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.refreshList(db.showIngredients());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new spDatabase(this);
        setContentView(R.layout.activity_pantry_management);



//      ADD INGREDIENT ======================================================
        Button addButton = findViewById(R.id.addIngredientButton);

        addButton.setOnClickListener(v -> {
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            EditText name = findViewById(R.id.nameTxt);
            EditText quantity = findViewById(R.id.quantityTxt);
            EditText unit = findViewById(R.id.unitsTxt);
            EditText expiryDate = findViewById(R.id.expiryDate);
            TextView message = findViewById(R.id.message);

            try {

                if (!name.getText().toString().isEmpty() && !quantity.getText().toString().isEmpty() && !unit.getText().toString().isEmpty() ) {

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    format.setLenient(false);

                    Date expiry = format.parse(expiryDate.getText().toString());
                    Date today = new Date();

                    if(!expiryDate.getText().toString().isBlank()){

                        if(expiry.before(today)) {

                            message.setText("Expiry date cannot be before today");
                            message.setVisibility(View.VISIBLE);
                            return;
                        }

                    }
                    if (Integer.parseInt(quantity.getText().toString()) <= 0) {
                        message.setText("Quantity must be greater than 0");
                        message.setVisibility(View.VISIBLE);
                        return;
                    }
                    ingredients.add(new Ingredient(name.getText().toString().trim(), Integer.parseInt(quantity.getText().toString().trim()), unit.getText().toString().trim(), expiryDate.getText().toString().trim()));

                    for (Ingredient ingredient : ingredients) {
                        db.addIngredient(ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit(), ingredient.getExpiry());

                        Log.d("added items", ingredient.getName());
                    }

                    message.setVisibility(View.GONE);
                    adapter.refreshList(db.showIngredients());
                }else{
                    message.setText("fill the entire form");
                    message.setVisibility(View.VISIBLE);
                }
            } catch (ParseException e) {

                message.setText("Invalid date");
                message.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {

                message.setText("Quantity must be a number");
                message.setVisibility(View.VISIBLE);
            }
        });

//      NAVIGATION BAR ======================================================
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNav);

        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigation, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), systemBars.bottom);

            return insets;
        });

        bottomNavigation.inflateMenu(R.menu.nav_menu);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(PantryManagement.this, PantryHome.class));
                return true;
            }

            if (id == R.id.nav_edit) {
                return true;
            }

            if (id == R.id.nav_recipes) {
                startActivity(new Intent(PantryManagement.this, SuggestedRecipesActivity.class));
                return true;
            }

            if (id == R.id.nav_settings) {
                startActivity(new Intent(PantryManagement.this, SettingsActivity.class));
                return true;
            }

            return false;
        });

        bottomNavigation.setSelectedItemId(R.id.nav_edit);

        //RECYCLE VIEW ======================================================
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Ingredient> ingredientArray = db.showIngredients();
        adapter = new ingredientAdapter(ingredientArray, ingredient -> {

            db.deleteIngredient(ingredient.getId());

            adapter.refreshList(db.showIngredients());
        }, ingredient -> {
            Intent intent = new Intent(PantryManagement.this, EditIngredientActivity.class);

            intent.putExtra("ingredient_id", ingredient.getId());
            intent.putExtra("ingredient_name", ingredient.getName());
            intent.putExtra("ingredient_quantity", ingredient.getQuantity());
            intent.putExtra("ingredient_unit", ingredient.getUnit());
            intent.putExtra("ingredient_expiry", ingredient.getExpiry());

            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}