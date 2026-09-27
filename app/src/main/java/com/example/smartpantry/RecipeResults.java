package com.example.smartpantry;

import java.util.ArrayList;

public class RecipeResults {

    ArrayList<Integer> matching;
    ArrayList<AlmostMatch> almostMatching;

    public RecipeResults(ArrayList<Integer> matching,
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
    }

}