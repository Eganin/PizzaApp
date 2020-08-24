package com.example.pizzaapp;

import android.content.Context;
import android.content.Intent;
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
    Context context;// ссылка на activity

    public PizzaRecipeAdapter(ArrayList<PizzaRecipeItem> arrayList,
                              Context context){
        /*
        передаем массив с результатом для заполнения CardView
         */
        pizzaRecipeItems = arrayList;
        this.context=context;
    }

    class PizzaRecipeViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{// переопределяем метод интерфейса чтобы регистрировать нажатия
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
            itemView.setOnClickListener(this);// установить обработчик на CardView

            this.imageView = itemView.findViewById(R.id.pizzaImageView);
            this.textView1 = itemView.findViewById(R.id.titleImageView);
            this.textView2 = itemView.findViewById(R.id.descriptionImageView);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();// получаем позицию CardView
            PizzaRecipeItem pizzaRecipeItem = pizzaRecipeItems.get(position); // получаем данные

            // перенаправляем из MainActivity в RecipeActivity
            Intent intent = new Intent(context,RecipeActivity.class);
            pushDataRecipeActivity(pizzaRecipeItem , intent);

            context.startActivity(intent);// start new activity
        }

        private void pushDataRecipeActivity(PizzaRecipeItem data , Intent intent){
            String title = data.getTitle();
            String recipe = data.getRecipe();
            String description = data.getDescription();
            int imageResource = data.getImageResource();
            intent.putExtra("imageResource",imageResource);
            intent.putExtra("title",title);
            intent.putExtra("recipe",recipe);
            intent.putExtra("description",description);
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
