package com.example.gameapp.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.gameapp.DAO.CommentDAO;
import com.example.gameapp.DAO.GameDAO;
import com.example.gameapp.Model.Comment;
import com.example.gameapp.Model.Game;

@Database(entities = {Game.class, Comment.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {

    private static GameDatabase instance;

    public abstract GameDAO gameDao();
    public abstract CommentDAO commentDao();

    //synchronized = 1 thread a la fois que atteindre la BDD. Au cas ou
    //on crée plusieurs instance,
    public static synchronized GameDatabase getInstance(Context context)
    {
        //On veut seulement l'instancer si elle n'est pas deja instancé
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GameDatabase.class, "game_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        //Si la BDD a deja été instancer, on retourne l'instance actuelle
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //Ajouter des données de base dans la DB
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private GameDAO gameDao;
        private CommentDAO commentDao;

        private PopulateDbAsyncTask(GameDatabase db)
        {
            gameDao = db.gameDao();
            commentDao = db.commentDao();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            //Données table Jeux
            gameDao.insert(new Game("Pac-man", "Description 1", 3, "Action", "/storage/emulated/0/DCIM/Camera/IMG_20190330_102504.jpg", 20100325));
            gameDao.insert(new Game("Mario Kart", "Description 2", 2, "Action", "/storage/emulated/0/DCIM/Camera/IMG_20190330_102504.jpg", 20110325));
            gameDao.insert(new Game("Pokemon", "Description 3", 1, "Action", "/storage/emulated/0/DCIM/Camera/IMG_20190330_102504.jpg", 20120325));

            //Données table Comments
            commentDao.insert(new Comment("Good Game !", "Valentine", 1));
            commentDao.insert(new Comment("Very Good Game !", "Yannick", 2));

            return null;
        }
    }
}
