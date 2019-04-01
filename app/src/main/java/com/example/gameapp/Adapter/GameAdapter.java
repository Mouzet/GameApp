package com.example.gameapp.Adapter;

import android.net.Uri;
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
                .inflate(R.layout.activity_games, parent, false);
        return new GameHolder(itemView);
    }
    // Method utilisé pour le delete //Yannick
    public Game getGameAt(int position){
        return games.get(position);
    }

    //Get the data into the view GameHolder
    @Override
    public void onBindViewHolder(@NonNull GameHolder gameHolder, int position)
    {


        Game currentGame = games.get(position);
        gameHolder.textViewName.setText(currentGame.getNameGame());
        gameHolder.textViewDescription.setText(currentGame.getDescriptionGame());

        gameHolder.buttonImage.setImageURI(Uri.parse(currentGame.getPathImage()));
        gameHolder.pathImage.setText(currentGame.getPathImage());
        gameHolder.textViewDate.setText(String.valueOf(currentGame.getDate()));
        gameHolder.textViewGender.setText(currentGame.getGenderGame());
        gameHolder.textViewstars.setText(String.valueOf(currentGame.getNumberStars()));
        gameHolder.textIdGame.setText((String.valueOf(currentGame.getIdGame())));

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

        private ImageView buttonImage;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewDate;
        private TextView textViewGender;
        private TextView pathImage;
        private TextView textViewstars;
        private TextView textIdGame;

        public GameHolder(@NonNull View itemView) {

            super(itemView);

            pathImage= itemView.findViewById(R.id.pathimage);
            buttonImage = itemView.findViewById(R.id.buttonimage);
            textViewName = itemView.findViewById(R.id.name_game);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewDate = itemView.findViewById(R.id.date);
            textViewGender = itemView.findViewById(R.id.genre);
            textViewstars = itemView.findViewById(R.id.number);
            textIdGame = itemView.findViewById(R.id.id_game);

        }
    }
}
