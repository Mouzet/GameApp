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
import com.example.gameapp.entity.Comment;
import com.example.gameapp.util.OnAsyncEventListener;

public class CommentViewModel extends AndroidViewModel {
    private CommentRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<Comment> mObservableComment;

    public CommentViewModel(@NonNull Application application,
                            final String commentId, CommentRepository commentRepository) {
        super(application);

        mRepository = commentRepository;

        mObservableComment = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableComment.setValue(null);

        if (commentId != null) {
            LiveData<Comment> comment = mRepository.getComment(commentId);

            // observe the changes of the account entity from the database and forward them
            mObservableComment.addSource(comment, mObservableComment::setValue);
        }
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mCommentId;

        private final CommentRepository mRepository;

        public Factory(@NonNull Application application, String commentId) {
            mApplication = application;
            mCommentId = commentId;
            mRepository = ((BaseApp) application).getCommentRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CommentViewModel(mApplication, mCommentId, mRepository);
        }
    }

    /**
     * Expose the LiveData AccountEntity query so the UI can observe it.
     */
    public LiveData<Comment> getComment() {
        return mObservableComment;
    }

    public void createComment(Comment comment, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getCommentRepository()
                .insert(comment, callback);
    }

    public void deleteComment(Comment comment, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getCommentRepository()
                .delete(comment, callback);
    }
}

