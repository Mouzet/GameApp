package com.example.gameapp.Controller;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.gameapp.Adapter.CommentAdapter;
import com.example.gameapp.Model.Comment;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.CommentViewModel;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private CommentViewModel commentViewModel;

    private String userComment;
    private String textComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_comments);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CommentAdapter adapter = new CommentAdapter();
        recyclerView.setAdapter(adapter);

        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        commentViewModel.getAllComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(@Nullable List<Comment> comments) {
                adapter.setComments(comments);
            }
        });


    }
}
