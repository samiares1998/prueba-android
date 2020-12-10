package co.com.ceiba.mobile.pruebadeingreso.model.interactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.UsuariosApi;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.LisPostsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPostsInteractor  implements IListPosts.Interactor, Callback<List<Post>> {
    private IListPosts.Presenter presenter;
    private List<Post> posts;
    private UsuariosApi apiService;

    public ListPostsInteractor(LisPostsPresenter lisPostsPresenter) {
        this.presenter = lisPostsPresenter;
    }

    @Override
    public void setPresenter(IListPosts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPosts(int id) {
        Call<List<Post>> call = ApiAdapter.getApiService().getPostsUser(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()){
            posts = response.body();
            if(posts != null) {
                System.out.println("......-----------------------------------...........");
                System.out.println(presenter);
                presenter.showPosts((ArrayList<Post>) posts);
            } else {
                Log.i("onResponseShowPosts", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        Log.e("onFailureShowPosts", "Response failed");
    }
}
