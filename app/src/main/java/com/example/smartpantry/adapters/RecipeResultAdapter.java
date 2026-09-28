package com.example.smartpantry.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.R;
import com.example.smartpantry.SuggestedRecipesActivity;

import java.util.ArrayList;

public class RecipeResultAdapter extends RecyclerView.Adapter<RecipeResultAdapter.RecipeViewHolder> {

    private final ArrayList<SuggestedRecipesActivity.Recipe> recipes;

    public RecipeResultAdapter(ArrayList<SuggestedRecipesActivity.Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_layout, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        SuggestedRecipesActivity.Recipe recipe = recipes.get(position);

        holder.recipeName.setText(recipe.getName());

        int imageId = holder.itemView.getContext().getResources().getIdentifier(
                recipe.getImage(),
                "drawable",
                holder.itemView.getContext().getPackageName()
        );

        holder.recipeImage.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeImage;
        TextView recipeName;
        TextView missingIngredient;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeName = itemView.findViewById(R.id.recipeName);
            missingIngredient = itemView.findViewById(R.id.missingIngredient);
        }
    }
}