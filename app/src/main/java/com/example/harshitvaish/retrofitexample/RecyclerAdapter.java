package com.example.harshitvaish.retrofitexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.itemHolder>{
    private List<Hero> HeroData;
    private LayoutInflater inflator;
    private Context context;

    public RecyclerAdapter(List<Hero> heroData, Context context) {
        HeroData = heroData;
        this.inflator =LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull

    @Override
    public RecyclerAdapter.itemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflator.inflate(R.layout.memu_item,viewGroup,false);
        itemHolder holder=new itemHolder(view);


        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.itemHolder itemHolder, int i) {
        Hero heroes=HeroData.get(i);
        itemHolder.setHeroes(heroes,i);
    }

    @Override
    public int getItemCount() {
        return HeroData.size();
    }
    public class itemHolder extends RecyclerView.ViewHolder{
        int position;
       ImageView imageurl;
       TextView name,bio;
        Hero currentMenuItem;

        public itemHolder(@NonNull View itemView) {
            super(itemView);
            imageurl=itemView.findViewById(R.id.imageurl);
            name=itemView.findViewById(R.id.name);
            bio=itemView.findViewById(R.id.bio);
        }
        public void setHeroes(Hero currentMenuItem,int position){
            this.position=position;
            this.currentMenuItem=currentMenuItem;
            name.setText(currentMenuItem.getName());
            bio.setText(currentMenuItem.getBio());

           //  imageurl.setImageResource(currentMenuItem.getImageurl());

        }
    }
}

