package com.example.gameapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameapp.Model.Game;
import com.example.gameapp.R;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder>
{
    //Nouvelle liste de jeux
    private List<Game> games = new ArrayList<>();

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.games, parent, false);
        return new GameHolder(itemView);
    }

    //Get the data into the view GameHolder
    @Override
    public void onBindViewHolder(@NonNull GameHolder gameHolder, int position)
    {
        Game currentGame = games.get(position);
        gameHolder.textViewName.setText(currentGame.getNameGame());
        gameHolder.textViewDescription.setText(currentGame.getDescriptionGame());
        gameHolder.textViewDate.setText(String.valueOf(currentGame.getDate()));

    }

    //Return how many item we want to display in our recycle view
    @Override
    public int getItemCount()
    {
        return games.size();
    }

    //For the LiveData
    public void setGames(List<Game> games)
    {
        this.games = games;
        notifyDataSetChanged();
    }

    class GameHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewPicture;
        private TextView textViewName;
        private TextView textViewDescription;
        private ImageView imageViewStars;
        private TextView textViewDate;

        public GameHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPicture = itemView.findViewById(R.id.image);
            textViewName = itemView.findViewById(R.id.name_game);
            textViewDescription = itemView.findViewById(R.id.description);
            imageViewStars = itemView.findViewById(R.id.stars);
            textViewDate = itemView.findViewById(R.id.date);

        }
    }
}