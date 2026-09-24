package com.example.smartpantry;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.MainActivity.Ingredient;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ingredientAdapter extends RecyclerView.Adapter<ingredientAdapter.MyViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Ingredient ingredient);
    }

    public interface OnEditClickListener {
        void onEditClick(Ingredient ingredient);
    }
    private List<Ingredient> ingredientList;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editListener;
    public ingredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public ingredientAdapter(List<Ingredient> ingredientList, OnDeleteClickListener deleteListener, OnEditClickListener editListener) {

        this.ingredientList = ingredientList;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ingredient ingredientItems = ingredientList.get(position);

        Log.d("ADAPTER", "Binding: " + ingredientItems.getName());

        holder.ingredientName.setText(ingredientItems.getName());

        holder.ingredientQuantity.setText(String.valueOf(ingredientItems.getQuantity()));

        holder.ingredientUnit.setText(ingredientItems.getUnit());

        holder.deleteButton.setOnClickListener(v -> {

            Log.d("DELETE", "Button clicked: " + ingredientItems.getName());
            Log.d("DELETE", "ID: " + ingredientItems.getId());

            if (deleteListener != null) {
                Log.d("DELETE", "Sending ingredient to MainActivity");
                deleteListener.onDeleteClick(ingredientItems);
            } else {
                Log.d("DELETE", "deleteListener is NULL");
            }
        });

        holder.editButton.setOnClickListener(v -> {

            Log.d("EDIT", "Button clicked: " + ingredientItems.getName());
            Log.d("EDIT", "ID: " + ingredientItems.getId());

            if (editListener != null) {
                Log.d("EDIT", "Sending ingredient to MainActivity");
                editListener.onEditClick(ingredientItems);
            } else {
                Log.d("EDIT", "editListener is NULL");
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
        MaterialButton deleteButton;
        MaterialButton editButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredientName);

            ingredientQuantity = itemView.findViewById(R.id.ingredientQuantity);

            ingredientUnit = itemView.findViewById(R.id.ingredientUnit);

            deleteButton = itemView.findViewById(R.id.delete);

            editButton = itemView.findViewById(R.id.edit);
        }
    }
}