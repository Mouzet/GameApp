package com.example.gameapp.Controller;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.gameapp.Adapter.CommentAdapter;
import com.example.gameapp.Model.Comment;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.CommentViewModel;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private CommentViewModel commentViewModel;

    public final static int ADD_COMMENT_REQUEST =1;

    private String userComment;
    private String textComment;
    private int idGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_comments);

        FloatingActionButton buttonAdd = findViewById(R.id.add_comment);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentsActivity.this, Add_CommentActivity.class);
                startActivityForResult(intent, ADD_COMMENT_REQUEST);
            }
        });

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


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                commentViewModel.delete(adapter.getCommentAt(viewHolder.getAdapterPosition()));

                Toast.makeText(CommentsActivity.this, "Comment deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_COMMENT_REQUEST && resultCode == RESULT_OK){
            String user = data.getStringExtra(Add_CommentActivity.EXTRA_USER);
            String comment = data.getStringExtra(Add_CommentActivity.EXTRA_COMMENT);

            Comment c = new Comment(comment,user, idGame);
            commentViewModel.insert(c);

            Toast.makeText(this, "Comment saved", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Comment not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
