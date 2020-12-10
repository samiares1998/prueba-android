package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public interface IListUser {

    interface View{
    void showUsers(ArrayList<User> users);

        void updateList(ArrayList<User> users);
    }

        interface Presenter{
        void setView(IListUser.View view);
        void getUsers();
        void showUsers(ArrayList<User> users);
        void searchUser(String text, ArrayList<User> users);
        void updateList(ArrayList<User> users);
    }

    interface Interactor{
        void setPresenter(IListUser.Presenter presenter);
        void getUsers();


        void searchUser(String text, ArrayList<User> users);
    }

}
