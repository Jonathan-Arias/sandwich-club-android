package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            String fallback = "Unknown";
            JSONObject sandwichDetails = new JSONObject(json);
            JSONObject name = sandwichDetails.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            String placeOfOrigin = sandwichDetails.getString("placeOfOrigin");
            String description = sandwichDetails.getString("description");
            String image = sandwichDetails.getString("image");
            JSONArray ingredients = sandwichDetails.getJSONArray("ingredients");

            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            List<String> ingredientList = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++){
                ingredientList.add(ingredients.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientList);
            return sandwich;

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
