package co.com.ceiba.mobile.pruebadeingreso.presenter.posts;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;

public interface IListPosts {
    interface View {
        void showPosts(ArrayList<Post> posts);
    }

    interface Presenter {
        void setView(IListPosts.View view);
        void getPosts(int id);
        void showPosts(ArrayList<Post> posts);
    }

    interface Interactor {
        void setPresenter(IListPosts.Presenter presenter);
        void getPosts(int id);
    }
}
