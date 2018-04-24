package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        // Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        String name = "Club sandwich";
        ArrayList<String> aka = new ArrayList<String>();
        aka.add("Clubhouse sandwich");
        String origin = "United States";
        String description = "A club sandwich, also called a clubhouse sandwich, is a sandwich of bread (occasionally toasted), sliced cooked poultry, fried bacon, lettuce, tomato, and mayonnaise. It is often cut into quarters or halves and held together by cocktail sticks. Modern versions frequently have two layers which are separated by an additional slice of bread.";
        String image = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png";
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Toasted bread");
        ingredients.add("Turkey or chicken");
        ingredients.add("Bacon");
        ingredients.add("Lettuce");
        ingredients.add("Tomato");
        ingredients.add("Mayonnaise");
        mSandwich = new Sandwich(name, aka, origin, description, image, ingredients);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(ingredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        TextView mOrigin = findViewById(R.id.origin_tv);
        TextView mDescription = findViewById(R.id.description_tv);
        TextView mIngredients = findViewById(R.id.ingredients_tv);
        TextView mAka = findViewById(R.id.also_known_tv);
        mOrigin.setText(mSandwich.getPlaceOfOrigin());
        mDescription.setText(mSandwich.getDescription());
        for (String s : mSandwich.getIngredients()){
            mIngredients.append(s);
        }
        for (String s : mSandwich.getAlsoKnownAs()){
            mAka.append(s);
        }
    }
}
