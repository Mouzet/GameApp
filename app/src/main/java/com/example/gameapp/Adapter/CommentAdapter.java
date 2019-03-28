package com.example.gameapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameapp.Model.Comment;
import com.example.gameapp.Model.Game;
import com.example.gameapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>
{
    //Nouvelle liste de jeux
    private List<Comment> comments = new ArrayList<>();

    @NonNull
    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_comments, parent, false);
        return new CommentAdapter.CommentHolder(itemView);
    }
    // Method utilisé pour le delete //Yannick
    public Comment getCommentAt(int position){
        return comments.get(position);
    }
    //Get the data into the view GameHolder
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder commentHolder, int position)
    {
        //???? Comment faire les images?

        Comment currentComment = comments.get(position);
        commentHolder.textViewUser.setText(currentComment.getNameAuthorComment());
        commentHolder.textViewText.setText(currentComment.getTextComment());

    }

    //Return how many item we want to display in our recycle view
    @Override
    public int getItemCount()
    {
        return comments.size();
    }

    //For the LiveData
    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
        notifyDataSetChanged();
    }

    class CommentHolder extends RecyclerView.ViewHolder{

        private TextView textViewUser;
        private TextView textViewText;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            textViewUser = itemView.findViewById(R.id.user_comment);
            textViewText = itemView.findViewById(R.id.text_comment);
        }
    }
}
