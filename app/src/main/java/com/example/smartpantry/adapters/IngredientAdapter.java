package com.example.smartpantry.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.PantryManagement.Ingredient;
import com.example.smartpantry.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Ingredient ingredient);
    }

    public interface OnEditClickListener {
        void onEditClick(Ingredient ingredient);
    }

    private List<Ingredient> ingredientList;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editListener;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public IngredientAdapter(
            List<Ingredient> ingredientList,
            OnDeleteClickListener deleteListener,
            OnEditClickListener editListener) {

        this.ingredientList = ingredientList;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MyViewHolder holder,
            int position) {

        Ingredient ingredientItems = ingredientList.get(position);

        Log.d("ADAPTER", "Binding: " + ingredientItems.getName());

        holder.ingredientName.setText(ingredientItems.getName());

        holder.ingredientQuantity.setText(
                String.valueOf(ingredientItems.getQuantity())
        );

        holder.ingredientUnit.setText(ingredientItems.getUnit());

        holder.ingredientExpiry.setText(ingredientItems.getExpiry());

        holder.deleteButton.setOnClickListener(v -> {

            Log.d("DELETE", "Button Clicked: " + ingredientItems.getName());
            Log.d("DELETE", "ID: " + ingredientItems.getId());

            if (deleteListener != null) {
                deleteListener.onDeleteClick(ingredientItems);
            }
        });

        holder.editButton.setOnClickListener(v -> {

            Log.d("EDIT", "Button clicked: " + ingredientItems.getName());
            Log.d("EDIT", "ID: " + ingredientItems.getId());

            if (editListener != null) {
                editListener.onEditClick(ingredientItems);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void refreshList(List<Ingredient> newList) {
        ingredientList = newList;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientQuantity;
        TextView ingredientUnit;
        TextView ingredientExpiry;

        MaterialButton deleteButton;
        MaterialButton editButton;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            ingredientName =
                    itemView.findViewById(R.id.ingredientName);

            ingredientQuantity =
                    itemView.findViewById(R.id.ingredientQuantity);

            ingredientUnit =
                    itemView.findViewById(R.id.ingredientUnit);

            ingredientExpiry =
                    itemView.findViewById(R.id.ingredientExpire);

            deleteButton =
                    itemView.findViewById(R.id.delete);

            editButton =
                    itemView.findViewById(R.id.edit);
        }
    }
}