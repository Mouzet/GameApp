package com.example.gameapp.Adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gameapp.R;
import com.example.gameapp.entity.Comment;
import com.example.gameapp.entity.Game;
import com.example.gameapp.util.RecyclerViewItemClickListener;

import java.util.List;
import java.util.Objects;

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> data;
    private RecyclerViewItemClickListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textView;
        ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_result_games, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> listener.onItemClick(view, viewHolder.getAdapterPosition()));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        T item = data.get(position);
        if (item.getClass().equals(Comment.class))
            holder.textView.setText(((Comment) item).getUserComment());
        if (item.getClass().equals(Game.class))
            holder.textView.setText(((Game) item).getNameGame());
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (this.data == null) {
            this.data = data;
            notifyItemRangeInserted(0, getItemCount());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return RecyclerAdapter.this.data.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (RecyclerAdapter.this.data instanceof Comment) {
                        return ((Comment) RecyclerAdapter.this.data.get(oldItemPosition)).getIdComment().equals(((Comment) data.get(newItemPosition)).getIdComment());
                    }
                    if (RecyclerAdapter.this.data instanceof Game) {
                        return ((Game) RecyclerAdapter.this.data.get(oldItemPosition)).getNameGame().equals(
                                ((Game) data.get(newItemPosition)).getNameGame());
                    }
                    return false;
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (RecyclerAdapter.this.data instanceof Comment) {
                        Comment newComment = (Comment) data.get(newItemPosition);
                        Comment oldComment = (Comment) RecyclerAdapter.this.data.get(newItemPosition);
                        return newComment.getIdComment().equals(oldComment.getIdComment())
                                && Objects.equals(newComment.getUserComment(), oldComment.getUserComment())
                                && Objects.equals(newComment.getTextComment(), oldComment.getTextComment())
                                && newComment.getIdGame().equals(oldComment.getIdGame());
                    }
                    if (RecyclerAdapter.this.data instanceof Game) {
                        Game newGame = (Game) data.get(newItemPosition);
                        Game oldGame = (Game) RecyclerAdapter.this.data.get(newItemPosition);
                        return Objects.equals(newGame.getNameGame(), oldGame.getNameGame())
                                && Objects.equals(newGame.getDescriptionGame(), oldGame.getDescriptionGame())
                                && Objects.equals(newGame.getNumberStars(), oldGame.getNumberStars())
                                && Objects.equals(newGame.getGenderGame(), oldGame.getGenderGame())
                                && Objects.equals(newGame.getPathImage(), oldGame.getPathImage())
                                && Objects.equals(newGame.getDate(), oldGame.getDate());
                    }
                    return false;
                }
            });
            this.data = data;
            result.dispatchUpdatesTo(this);
        }
    }
}
