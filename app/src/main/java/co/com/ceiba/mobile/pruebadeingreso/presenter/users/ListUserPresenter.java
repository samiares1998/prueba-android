package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import android.content.Context;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.interactors.ListUsersInteractor;

public class ListUserPresenter implements IListUser.Presenter {
    private IListUser.View view;
    private IListUser.Interactor iteractor;
    public Context con;
    public ListUserPresenter(IListUser.View view,Context c){
        this.view=view;
        this.con=c;

        iteractor = new ListUsersInteractor(this,c);
    }

    @Override
    public void setView(IListUser.View view) {

    }

    @Override
    public void getUsers() {


                if(iteractor!=null){
                    iteractor.getUsers();
                }else{
                    iteractor = new ListUsersInteractor(this,this.con);
                    iteractor.getUsers();

                }




    }

    @Override
    public void showUsers(ArrayList<User> users) {
        if(view!=null){
            view.showUsers(users);
        }
    }

    @Override
    public void searchUser(String text, ArrayList<User> users) {
        if (iteractor != null) {
            iteractor.searchUser(text,users);
        }
    }

    @Override
    public void updateList(ArrayList<User> users) {
        view.updateList(users);
    }

    public void setInteractor(IListUser.Interactor interactor, Context c) {
        this.view=view;
        this.con=c;

        iteractor = new ListUsersInteractor(this,c);
    }
}
