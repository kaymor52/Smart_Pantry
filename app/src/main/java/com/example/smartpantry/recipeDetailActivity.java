package com.example.smartpantry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantry.database.spDatabase;

import java.util.ArrayList;

public class recipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recipe_detailed_layout);

        // GET RECIPE ID
        int recipeId = getIntent().getIntExtra("recipe_id", -1);

        spDatabase db = new spDatabase(this);

        // GET RECIPE
        ArrayList<SuggestedRecipesActivity.Recipe> recipes =
                db.showRecipe(recipeId);

        if (!recipes.isEmpty()) {

            SuggestedRecipesActivity.Recipe recipe =
                    recipes.get(0);

            // GET RECIPE INGREDIENTS
            ArrayList<SuggestedRecipesActivity.RecipeIngredients> ingredients =
                    db.showRecipeIngredients(recipeId);

            // GET UI
            TextView recipeName = findViewById(R.id.textView10);
            TextView recipeDetails = findViewById(R.id.textView13);
            ImageView recipeImage = findViewById(R.id.imageView);

            // RECIPE NAME
            recipeName.setText(recipe.getName());

            // INGREDIENT LIST
            String ingredientList = "";

            for (SuggestedRecipesActivity.RecipeIngredients ingredient : ingredients) {

                ingredientList += ingredient.getQuantity()
                        + " "
                        + ingredient.getUnits()
                        + " "
                        + ingredient.getName()
                        + "\n";
            }

            // RECIPE DETAILS
            recipeDetails.setText(
                    "Preparation time: " + recipe.getPrepTime()
                            + "\nCook time: " + recipe.getCookTime()
                            + "\n\nIngredients:\n"
                            + ingredientList
                            + "\nInstructions:\n\n"
                            + recipe.getInstructions()
            );

            // RECIPE IMAGE
            int imageId = getResources().getIdentifier(
                    recipe.getImage(),
                    "drawable",
                    getPackageName()
            );

            recipeImage.setImageResource(imageId);
        }

        // CLOSE BUTTON
        Button close = findViewById(R.id.closeWindow);

        close.setOnClickListener(v -> finish());
    }
}