package co.com.ceiba.mobile.pruebadeingreso.presenter.posts;

import android.util.Log;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.interactors.ListPostsInteractor;

public class LisPostsPresenter implements IListPosts.Presenter{
    private IListPosts.View view;
    private IListPosts.Interactor interactor;

    public LisPostsPresenter(IListPosts.View v) {
        this.view = v;
        interactor = new ListPostsInteractor(this);

    }
    public void setInteractor(IListPosts.Interactor interactor) {
        this.interactor = interactor;
    }
    @Override
    public void setView(IListPosts.View view) {

    }

    @Override
    public void getPosts(int id) {
        interactor.getPosts(id);
    }

    @Override
    public void showPosts(ArrayList<Post> posts) {
        if (view != null) {
            view.showPosts(posts);
        } else {
            Log.i("showPostsPresenter", "View is null");
        }
    }
}
