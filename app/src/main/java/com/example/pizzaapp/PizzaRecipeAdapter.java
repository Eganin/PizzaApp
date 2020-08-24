package com.example.pizzaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PizzaRecipeAdapter
        extends RecyclerView.Adapter<PizzaRecipeAdapter.PizzaRecipeViewHolder> {

    ArrayList<PizzaRecipeItem> pizzaRecipeItems;

    public PizzaRecipeAdapter(ArrayList<PizzaRecipeItem> arrayList){
        /*
        передаем массив с результатом для заполнения CardView
         */
        pizzaRecipeItems = arrayList;
    }

    public static class PizzaRecipeViewHolder extends RecyclerView.ViewHolder{
        /*
        Наполняем подкласс данными
        для заполнения элемента
         */
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public PizzaRecipeViewHolder(@NonNull View itemView) {
            /*
        определяем элементы который будут находится в CardView
         */
            super(itemView);
            this.imageView = itemView.findViewById(R.id.pizzaImageView);
            this.textView1 = itemView.findViewById(R.id.titleImageView);
            this.textView2 = itemView.findViewById(R.id.descriptionImageView);
        }
    }

    @NonNull
    @Override
    public PizzaRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        если требуется создается новый элемент
         */
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pizza_recipe_item, parent, false);// указываем id карточки

        // создаем CardView
        PizzaRecipeViewHolder pizzaRecipeViewHolder = new PizzaRecipeViewHolder(view);
        return pizzaRecipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaRecipeViewHolder holder, int position) {
        /*
        Подстановка данных в CardView
         */
        PizzaRecipeItem pizzaRecipeItem = pizzaRecipeItems.get(position);

        // с помощью геттеров получаем данные из PizzaRecipeItem
        holder.imageView.setImageResource(pizzaRecipeItem.getImageResource());
        holder.textView1.setText(pizzaRecipeItem.getTitle());
        holder.textView2.setText(pizzaRecipeItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return pizzaRecipeItems.size();
    }

}
