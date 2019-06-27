package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_AKA = "alsoKnownAs";
        final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        Sandwich sandwich = new Sandwich();
        try {
            JSONObject object = new JSONObject(json);

            if (object.has(SANDWICH_NAME)) {
                JSONObject name = object.getJSONObject(SANDWICH_NAME);

                if (name.has(SANDWICH_MAIN_NAME))
                    sandwich.setMainName(name.getString(SANDWICH_MAIN_NAME));

                if (name.has(SANDWICH_AKA)) {
                    JSONArray array = name.getJSONArray(SANDWICH_AKA);
                    List<String> akasList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        akasList.add(array.getString(i));
                    }
                    sandwich.setAlsoKnownAs(akasList);
                }
            }

            if (object.has(SANDWICH_PLACE_OF_ORIGIN))
                sandwich.setPlaceOfOrigin(object.getString(SANDWICH_PLACE_OF_ORIGIN));

            if (object.has(SANDWICH_DESCRIPTION))
                sandwich.setDescription(object.getString(SANDWICH_DESCRIPTION));

            if (object.has(SANDWICH_IMAGE))
                sandwich.setImage(object.getString(SANDWICH_IMAGE));

            if (object.has(SANDWICH_INGREDIENTS)) {
                JSONArray mArray = object.getJSONArray(SANDWICH_INGREDIENTS);
                List<String> ingredientsList = new ArrayList<>();
                for (int i = 0; i < mArray.length(); i++) {
                    ingredientsList.add(mArray.getString(i));
                }
                sandwich.setIngredients(ingredientsList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
