package com.example.smartpantry.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.PantryManagement.Ingredient;
import com.example.smartpantry.R;

import java.util.List;

public class PantryListAdapter extends RecyclerView.Adapter<PantryListAdapter.MyViewHolder>{

    private List<Ingredient> ingredientList;

    public PantryListAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pantry_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ingredient ingredient = ingredientList.get(position);

        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientQuantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.ingredientUnit.setText(ingredient.getUnit());
        holder.ingredientExpire.setText(ingredient.getExpiry());
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

        TextView ingredientExpire;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientQuantity = itemView.findViewById(R.id.ingredientQuantity);
            ingredientUnit = itemView.findViewById(R.id.ingredientUnit);
            ingredientExpire= itemView.findViewById(R.id.ingredientExpire);
        }
    }
}