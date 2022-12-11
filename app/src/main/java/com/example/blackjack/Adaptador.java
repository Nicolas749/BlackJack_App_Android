package com.example.blackjack;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adaptador extends  RecyclerView.Adapter<Adaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cartaEnLista ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartaEnLista = (ImageView) itemView.findViewById(R.id.cartaEnLista);

        }
    }


    public ArrayList <Integer> intCards = new ArrayList<>();
    String tipo;

    public Adaptador( ArrayList intCards/*,String tipo*/){
        this.intCards = intCards;
        //this.tipo = tipo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carta_lista,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.cartaEnLista.setImageResource(intCards.get(i));
    }

    @Override
    public int getItemCount() {
        return intCards.size();
    }
}
