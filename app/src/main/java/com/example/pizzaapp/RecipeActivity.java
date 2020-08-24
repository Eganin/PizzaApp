package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {
    TextView title;
    TextView recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        title = findViewById(R.id.titleTextView);
        recipe = findViewById(R.id.recipeTextView);

        pullDataFromPizzaRecipeAdapter(title , recipe);
    }

    private void pullDataFromPizzaRecipeAdapter(TextView title, TextView recipe) {
        Intent intent = getIntent();
        if (intent != null) {
            title.setText(intent.getStringExtra("title"));
            recipe.setText(intent.getStringExtra("recipe"));
        }
    }
}