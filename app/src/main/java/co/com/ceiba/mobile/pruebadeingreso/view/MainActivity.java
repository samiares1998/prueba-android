package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUser;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.ListUserPresenter;
import co.com.ceiba.mobile.pruebadeingreso.utilities.Tools;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;

public class MainActivity extends Activity implements IListUser.View{
    private IListUser.Presenter presenter;
    private UserAdapter adapter;
    private ArrayList<User> users;
    Context context;
    RecyclerView recycler;
    RelativeLayout content;
    EditText search;
    private AlertDialog dialog;
    private View empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recyclerViewSearchResults);
        content = (RelativeLayout) findViewById(R.id.content);
        search = (EditText) findViewById(R.id.editTextSearch);
        dialog = Tools.showDialog(this).build();
        dialog.show();
        this.listUsers();
        presenter = new ListUserPresenter(this,getApplicationContext() );
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        makeTextWatcher();
        empty = getLayoutInflater().inflate(R.layout.empty_view, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.recyclerViewSearchResults);
        empty.setLayoutParams(params);



    }
    private void makeTextWatcher() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.searchUser(editable.toString(), users);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void listUsers(){
        if (presenter!=null) {
            presenter.getUsers();
        }else{
            presenter = new ListUserPresenter(this,getApplicationContext());
            presenter.getUsers();
        }

    }


    @Override
    public void showUsers(ArrayList<User> users) {
        this.users = users;
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(linear);
        adapter = new UserAdapter(users);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void updateList(ArrayList<User> users) {
        content.removeView(empty);
        if (users != null) {
            adapter.updateList(users);
            if (users.isEmpty()) {
                content.addView(empty);
            }
        }
    }

}