package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.LisPostsPresenter;
import co.com.ceiba.mobile.pruebadeingreso.utilities.Tools;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostAdapter;

public class PostActivity extends Activity implements IListPosts.View{

    Context context;

    TextView name;

    TextView phone;

    TextView email;

    RecyclerView recycler;

    private AlertDialog dialog;

    private IListPosts.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recycler = (RecyclerView) findViewById(R.id.recyclerViewPostsResults);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        presenter = new LisPostsPresenter(this);
        presenter.setView((IListPosts.View) this);
        int id = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            User u = (User) bundle.getSerializable("user");
            name.setText(u.getName());
            phone.setText(u.getPhone());
            email.setText(u.getEmail());
            id = u.getId();
        }

        dialog = Tools.showDialog(this).build();
        dialog.show();

        presenter.setView((IListPosts.View) this);
        presenter.getPosts(id);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void showPosts(ArrayList<Post> posts) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        recycler.setLayoutManager(linear);
        PostAdapter adapter = new PostAdapter(posts);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }


}
