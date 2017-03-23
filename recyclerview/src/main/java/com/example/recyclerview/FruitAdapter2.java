package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/** Created by mac_soong on 2017/2/22. */

public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {
    private List<Fruit> mFruitList;

    public FruitAdapter2(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView FruitImage;
        TextView FruitName;

        View fruitView;
        public ViewHolder(View itemView) {
            super(itemView);
            fruitView = itemView;
            FruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            FruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frute_style2, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fruit fruit = mFruitList.get(holder.getAdapterPosition());
                Toast.makeText(view.getContext(), "Name"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.FruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fruit fruit = mFruitList.get(holder.getAdapterPosition());
                Toast.makeText(view.getContext(), "Picture"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.FruitName.setText(fruit.getName());
        holder.FruitImage.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
