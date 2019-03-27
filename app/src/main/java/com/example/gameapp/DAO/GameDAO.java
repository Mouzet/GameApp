package com.example.gameapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gameapp.Model.Game;

import java.util.List;

//Database Access Objects
@Dao
public interface GameDAO {

        @Insert
    void insert(Game game);

    @Update
    void update(Game game);

    @Delete
    void delete(Game game);

    //Supprime tous les jeux de la BDD
    @Query("DELETE FROM game_table")
    void deleteAllGames();

    //On utilise LiveData pour que les données soient automatiquement
    //mise a jour lors d'un changement dans la BDD
    @Query("SELECT * FROM game_table")
    LiveData<List<Game>> getAllGames();

    //Requête pour la recherche
    //@Query("SELECT * FROM game_table WHERE mNameGame = nameSearch")
  //  LiveData<List<Game>> getResearchGames();

}
