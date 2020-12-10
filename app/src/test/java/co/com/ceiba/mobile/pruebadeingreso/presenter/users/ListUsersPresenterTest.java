package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersPresenterTest {

    @Mock
    Context mockContext;
    Context context = RuntimeEnvironment.systemContext;
    @Mock
    private IListUser.View view;
    @Mock
    private IListUser.Interactor interactor;
    @InjectMocks private ListUserPresenter presenter;

    @Before
    public void init() {
        context = RuntimeEnvironment.systemContext;
        presenter = new ListUserPresenter (view, mockContext.getApplicationContext());
        presenter.setView(view);
    }

    @Test
    public void getUsers() {
        this.presenter.getUsers();
        verify(interactor, times(1)).getUsers();
    }

    @Test
    public void showUsers() {
        User user = mock(User.class);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        this.presenter.showUsers(users);
        verify(view, times(1)).showUsers(users);
    }

    @Test
    public void searchUser() {
        String texto = "dummy";
        ArrayList<User> users = new ArrayList<>();
        this.presenter.searchUser(texto,users);
        verify(interactor, times(1)).searchUser(texto,users);
    }

    @Test
    public void updateList() {
        User user = mock(User.class);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        this.presenter.updateList(users);
        verify(view, times(1)).updateList(users);
    }

}