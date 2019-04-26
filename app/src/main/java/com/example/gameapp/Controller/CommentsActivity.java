package com.example.gameapp.Controller;


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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gameapp.Adapter.RecyclerAdapter;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.CommentListViewModel;
import com.example.gameapp.ViewModel.CommentViewModel;
import com.example.gameapp.entity.Comment;
import com.example.gameapp.util.OnAsyncEventListener;
import com.example.gameapp.util.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {



    public final static int ADD_COMMENT_REQUEST =1;

    private static final String TAG = "CommentsActivity";

    private List<Comment> comments;
    private RecyclerAdapter<Comment> commentAdapter;
    private CommentListViewModel commentViewModel;
    private CommentViewModel commentViewModel2;

    private String userComment;
    private String textComment;
    private String id_game;
    private String id_comment;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_comments);

        Intent intent = getIntent();

        id_game =intent.getStringExtra("idGame");

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


        comments = new ArrayList<>();
        commentAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent (CommentsActivity.this,Add_CommentActivity.class );
                intent.putExtra("Texte", comments.get(position).getTextComment());
                intent.putExtra("User", comments.get(position).getUserComment());
                intent.putExtra("IdComment", comments.get(position).getIdComment());
            }
        });

        CommentListViewModel.Factory factory = new CommentListViewModel.Factory(getApplication(),id_game,id_comment);
        commentViewModel = ViewModelProviders.of(this,factory).get(CommentListViewModel.class);
        commentViewModel.getCommentsByGameId().observe(this, commentEntities -> {
            if (commentEntities != null){
                comments = commentEntities;
                commentAdapter.setData(comments);
            }
        });

        recyclerView.setAdapter(commentAdapter);







        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                delete(viewHolder.getAdapterPosition());

                Toast.makeText(CommentsActivity.this, "Comment deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void delete(final int position){
        final Comment comment = comments.get(position);
        Toast toast = Toast.makeText(this, "Comment deleted",Toast.LENGTH_SHORT);

        commentViewModel.deleteComment(comment, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG,"Delete Comment : Success");
            }

            @Override
            public void onFailure(Exception e) {
            Log.d(TAG, "Delete comment : failure",e);
            }

        });
        toast.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_COMMENT_REQUEST && resultCode == RESULT_OK) {
            String user = data.getStringExtra(Add_CommentActivity.EXTRA_USER);
            String comment = data.getStringExtra(Add_CommentActivity.EXTRA_COMMENT);

            Comment c = new Comment();
            c.setIdGame(id_game);
            c.setTextComment(textComment);
            c.setUserComment(userComment);
            commentViewModel2.createComment(c, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "Add Comment : Success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "Add Comment : Success");
                }
            });

        }}

}
