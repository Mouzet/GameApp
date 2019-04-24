package com.example.gameapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gameapp.database.database.Entity.Comment;
import com.example.gameapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>
{
    //Nouvelle liste de commentaires
    private List<Comment> comments = new ArrayList<>();

    @NonNull
    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_comments, parent, false);
        return new CommentHolder(itemView);
    }

    // Method utilisé pour le delete //Yannick
    public Comment getCommentAt(int position){
        return comments.get(position);
    }
    //Get the data into the view GameHolder
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder commentHolder, int position)
    {
        Comment currentComment = comments.get(position);

        commentHolder.textViewIdComment.setText(String.valueOf(currentComment.getIdComment()));
        commentHolder.textViewUser.setText(currentComment.getUserComment());
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

    class CommentHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewIdComment;
        private TextView textViewUser;
        private TextView textViewText;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);

            textViewIdComment = itemView.findViewById(R.id.id_comment);
            textViewUser = itemView.findViewById(R.id.user_comment);
            textViewText = itemView.findViewById(R.id.text_comment);
        }
    }
}
