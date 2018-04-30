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
    private static String fallback = "Unknown";
    private static String noData = "No data available";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichDetails = new JSONObject(json);

            JSONObject name = sandwichDetails.getJSONObject("name");

            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsList = name.getJSONArray("alsoKnownAs");

            String placeOfOrigin = sandwichDetails.getString("placeOfOrigin");
            if (placeOfOrigin != null && placeOfOrigin.isEmpty()){
                placeOfOrigin = fallback;
            }

            String description = sandwichDetails.getString("description");
            String image = sandwichDetails.getString("image");
            JSONArray ingredientsList = sandwichDetails.getJSONArray("ingredients");

            List<String> alsoKnownAs = new ArrayList<>();
            addItemsToList(alsoKnownAsList, alsoKnownAs);

            List<String> ingredients = new ArrayList<>();
            addItemsToList(ingredientsList, ingredients);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static void addItemsToList(JSONArray items, List<String> list){
        if (items.length() == 0){
            list.add(noData);
        } else {
            try {
                for (int i = 0; i < items.length(); i++){
                    list.add(items.getString(i));
                }
            } catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }
}
