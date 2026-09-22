package com.example.smartpantry;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.database.spDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    spDatabase db;
    ingredientAdapter adapter;

    public static class Ingredient {
       private int id;
        private String name;
        private  int quantity;
        private  String unit;

    public Ingredient( String name, int quantity, String unit){

        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    public Ingredient(Integer id, String name, int quantity, String unit){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getUnit(){
        return unit;
    }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new spDatabase(this);

        Button addButton = findViewById(R.id.addIngredientButton);

        addButton.setOnClickListener(v -> {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            try {
                EditText name = findViewById(R.id.nameTxt);
                EditText quantity = findViewById(R.id.quantityTxt);
                EditText unit = findViewById(R.id.unitsTxt);
            //TODO ADD INPUT RULES ------------------->>
                ingredients.add(new Ingredient(
                        name.getText().toString(),
                        Integer.parseInt(quantity.getText().toString()),
                        unit.getText().toString()));

                for (Ingredient ingredient : ingredients) {
                    db.addIngredient(
                            ingredient.getName(),
                            ingredient.getQuantity(),
                            ingredient.getUnit());
                    Log.d("added items",ingredient.getName());
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

        });

      //RECYCLE VIEW ======================================================
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Ingredient> ingredientArray = db.showIngredients();
        adapter = new ingredientAdapter(
                ingredientArray,
                ingredient -> {

                    db.deleteIngredient(ingredient.getId());

                    adapter.refreshList(db.showIngredients());
                }
        );

        recyclerView.setAdapter(adapter);


    }

    public void displayIngredients(View v) {

        TextView text = findViewById(R.id.ingredientUnit);

        Button testbutton = findViewById(R.id.delete);


    }
}