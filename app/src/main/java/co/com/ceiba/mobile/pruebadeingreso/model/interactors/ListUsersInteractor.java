package co.com.ceiba.mobile.pruebadeingreso.model.interactors;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiAdapter;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersInteractor implements IListUser.Interactor, Callback<List<User>> {

    private IListUser.Presenter presenter;
    private List<User> users;
    private UserDto uDto;

    public ListUsersInteractor(IListUser.Presenter presenter, Context con) {
        this.presenter = presenter;

        this.uDto = new UserDto(con);

    }


    @Override
    public void setPresenter(IListUser.Presenter presenter) {

    }

    @Override
    public void getUsers() {

        if (uDto != null) {
            if (uDto.getUsers().isEmpty()) {
                Call<List<User>> call = ApiAdapter.getApiService().getUsers();
                call.enqueue(this);
            }else{
                getLocalUsers();
                presenter.showUsers((ArrayList<User>)users);
            }
        } else {
            Log.i("getUsersInteractor", "users null");
        }


    }

    @Override
    public void searchUser(String text, ArrayList<User> listUser) {
        ArrayList<User> temp = new ArrayList<>();

        if( listUser != null) {

            for (User s : listUser) {
                if (s.getName().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(s);
                }
            }
        }

        presenter.updateList(temp);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()){
            this.users = response.body();

            if(users != null) {
                saveUsers();
                presenter.showUsers((ArrayList<User>)users);

            } else {
                Log.i("onResponseShowUsers", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Log.e("onFailureShowUsers", "Response failed");
    }

    private void saveUsers(){
        if( users != null) {
            for (User u: users) {
                UserDB us = new UserDB(
                        u.getId(),
                        u.getName(),
                        u.getPhone(),
                        u.getEmail());
                uDto.addUser(us);
            }
        } else {
            Log.i("saveUsers", "Users is null");
        }

    }

    private void getLocalUsers(){
        if(uDto != null) {
            users = new ArrayList<User>();
            for (UserDB u: uDto.getUsers()) {


                User use = new User();
                use.setName(u.getName());

                use.setPhone(u.getPhone());
                use.setId(u.getId());
                use.setEmail(u.getEmail());
                users.add(use);
            }
        } else {
            Log.i("getLocalUsers", "UsersDto is null");
        }

    }
}
