package com.example.pizzaapp;

public class PizzaRecipeItem {
    /*
    Класс для хранения результатов для заполнения Card View в
    Recycle View
     */

    private int imageResource;// id изображения
    private String title;
    private String description;
    private String recipe;

    public PizzaRecipeItem(int imageResource, String title, String description , String recipe) {
        this.imageResource = imageResource;
        this.title = title;
        this.description= description;
        this.recipe = recipe;
    }

    /*
    getters and setters
     */
    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
