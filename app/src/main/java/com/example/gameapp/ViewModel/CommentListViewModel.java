package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.gameapp.BaseApp;
import com.example.gameapp.Repositories.CommentRepository;
import com.example.gameapp.Repositories.GameRepository;
import com.example.gameapp.entity.Comment;
import com.example.gameapp.util.OnAsyncEventListener;

import java.util.List;

public class CommentListViewModel extends AndroidViewModel {

    private static final String TAG = "CommentListViewModel";

    private CommentRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Comment>> mObservableComments;
    private final MediatorLiveData<Comment> mObservableComment;


    public CommentListViewModel(@NonNull Application application, final String mIdComment,
                                final String mIdGame, GameRepository gameRepository, CommentRepository commentRepository) {
        super(application);

        mRepository = commentRepository;

        mObservableComments = new MediatorLiveData<>();
        mObservableComment = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mObservableComments.setValue(null);
        mObservableComment.setValue(null);

        LiveData<List<Comment>> comments = mRepository.getCommentsByGameId(mIdGame);
        LiveData<Comment> comment = mRepository.getComment(mIdComment);

        // observe the changes of the entities from the database and forward them
        mObservableComments.addSource(comments, mObservableComments::setValue);
        mObservableComment.addSource(comment, mObservableComment::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mIdGame;
        private final String mIdComment;

        private final GameRepository mGameRepository;

        private final CommentRepository mCommentRepository;

        public Factory(@NonNull Application application, String idGame, String idComment) {
            mApplication = application;
            mIdGame= idGame;
            mIdComment = idComment;
            mGameRepository = ((BaseApp) application).getGameRepository();
            mCommentRepository = ((BaseApp) application).getCommentRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CommentListViewModel(mApplication, mIdGame, mIdComment, mGameRepository, mCommentRepository);
        }
    }



    public LiveData<Comment> getComment() {
        return mObservableComment;
    }

    public LiveData<List<Comment>> getCommentsByGameId() {
        return mObservableComments;
    }

    public void deleteComment(Comment comment, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getCommentRepository()
                .delete(comment, callback);
    }

}
