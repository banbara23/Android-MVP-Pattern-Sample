package r2android.apps.habanero.main;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import r2android.apps.habanero.R;
import r2android.apps.habanero.presentation.MainPresenter;
import r2android.apps.habanero.view.MainView;

import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class MainPresenterTest {

    private MainView viewMock;

    @Before
    public void setup() {
        viewMock = mock(MainView.class);
    }

    @Test
    public void onCreateTest() {
        MainPresenter presenter = new MainPresenter();
        presenter.onCreate(viewMock);
        verify(viewMock, times(1)).initViews();
    }

    @Test
    public void onOptionsItemSelectedTest() {
        MainPresenter presenter = new MainPresenter();
        presenter.onCreate(viewMock);
        verify(viewMock, never()).openDrawers();

        presenter.onOptionsItemSelected(android.R.id.home);
        verify(viewMock, times(1)).openDrawers();
    }

    @Test
    public void onNavigationItemSelected() {
        MainPresenter presenter = new MainPresenter();
        presenter.onCreate(viewMock);
        verify(viewMock, never()).showServiceAreaList();
        verify(viewMock, never()).showFavoriteList();

        presenter.onNavigationItemSelected(R.id.search_from_area);
        verify(viewMock, times(1)).showServiceAreaList();
        verify(viewMock, never()).showFavoriteList();

        presenter.onNavigationItemSelected(R.id.select_from_bookmark);
        verify(viewMock, times(1)).showServiceAreaList();
        verify(viewMock, times(1)).showFavoriteList();
    }
}
