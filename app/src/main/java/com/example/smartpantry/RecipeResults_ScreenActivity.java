package com.example.smartpantry;

import java.util.ArrayList;

public class RecipeResults_ScreenActivity {

    ArrayList<Integer> matching;
    ArrayList<AlmostMatch> almostMatching;

    public ArrayList<Integer> getMatching(){
        return matching;
    }
    public ArrayList<AlmostMatch> getAlmostMatching(){
        return almostMatching;
    }

    public RecipeResults_ScreenActivity(ArrayList<Integer> matching,
                                        ArrayList<AlmostMatch> almostMatching) {
        this.matching = matching;
        this.almostMatching = almostMatching;
    }

    public static class AlmostMatch {

        int recipeId;
        int ingredientId;

        public AlmostMatch(int recipeId, int ingredientId) {
            this.recipeId = recipeId;
            this.ingredientId = ingredientId;
        }
        public int getRecipeId() {
            return recipeId;
        }

        public int getIngredientId() {
            return ingredientId;
        }

    }

}