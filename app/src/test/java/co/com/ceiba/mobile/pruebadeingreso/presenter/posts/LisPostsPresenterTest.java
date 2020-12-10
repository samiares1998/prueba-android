package co.com.ceiba.mobile.pruebadeingreso.presenter.posts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LisPostsPresenterTest {

    @Mock
    private IListPosts.View view;
    @Mock
    private IListPosts.Interactor interactor;
    @InjectMocks
    private LisPostsPresenter presenter;

    @Before
    public void init() {
        this.presenter.setInteractor(interactor);
        this.presenter.setView(view);
    }

    @Test
    public void getPosts() {
        this.presenter.getPosts(anyInt());
        verify(interactor, times(1)).getPosts(anyInt());
    }

    @Test
    public void showPosts() {
        Post post = mock(Post.class);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        this.presenter.showPosts(posts);
        verify(view, times(1)).showPosts(posts);
    }
}