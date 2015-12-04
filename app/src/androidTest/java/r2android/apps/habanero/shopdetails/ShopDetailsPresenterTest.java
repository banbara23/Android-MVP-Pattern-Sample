package r2android.apps.habanero.shopdetails;

import android.graphics.Bitmap;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import r2android.apps.habanero.R;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.ShopDetailsUseCase;
import r2android.apps.habanero.presentation.ShopDetailsPresenter;
import r2android.apps.habanero.view.ShopDetailsView;

import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class ShopDetailsPresenterTest {

    private ShopDetailsUseCase interactorMock;
    private ShopDetailsView shopDetailsViewMock;
    private ShopData shopData;

    @Before
    public void setup() {
        interactorMock = mock(ShopDetailsUseCase.class);
        shopDetailsViewMock = mock(ShopDetailsView.class);

        shopData = new ShopData();
        shopData.id = "1";
    }

    @Test
    public void onCreateTestNoBitmap() {
        ShopDetailsPresenter presenter = new ShopDetailsPresenter(interactorMock);
        presenter.onCreate(shopDetailsViewMock, shopData, null);
        verify(shopDetailsViewMock, times(1)).initViews(shopData);
        verify(shopDetailsViewMock, never()).setLargeImageWithPlaceholder(eq(shopData), any(Bitmap.class));
        verify(shopDetailsViewMock, times(1)).setLargeImage(shopData);
        verify(shopDetailsViewMock, never()).setFabImage(R.drawable.ic_turned_in_white_24dp);
    }

    @Test
    public void onCreateTestWithBitmap() {
        ShopDetailsPresenter presenter = new ShopDetailsPresenter(interactorMock);
        presenter.onCreate(shopDetailsViewMock, shopData, Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8));
        verify(shopDetailsViewMock, times(1)).initViews(shopData);
        verify(shopDetailsViewMock, times(1)).setLargeImageWithPlaceholder(eq(shopData), any(Bitmap.class));
        verify(shopDetailsViewMock, never()).setLargeImage(shopData);
        verify(shopDetailsViewMock, never()).setFabImage(R.drawable.ic_turned_in_white_24dp);
    }

    @Test
    public void menuHomeClickTest() {
        ShopDetailsPresenter presenter = new ShopDetailsPresenter(interactorMock);
        presenter.onCreate(shopDetailsViewMock, shopData, null);
        presenter.onNavigationItemSelected(android.R.id.home);
        verify(shopDetailsViewMock, times(1)).upHome();
    }

    @Test
    public void hasShopDataTest() {
        ShopDetailsPresenter presenter = new ShopDetailsPresenter(interactorMock);
        presenter.onCreate(shopDetailsViewMock, shopData, null);
        when(interactorMock.isShopExists("1")).thenReturn(true);
        presenter.onCreate(shopDetailsViewMock, shopData, null);
        verify(shopDetailsViewMock, times(1)).setFabImage(R.drawable.ic_turned_in_white_24dp);
    }

    @Test
    public void onClickFabTest() {
        ShopDetailsPresenter presenter = new ShopDetailsPresenter(interactorMock);
        presenter.onCreate(shopDetailsViewMock, shopData, null);
        when(interactorMock.isShopExists("1")).thenReturn(false);
        presenter.onClick();
        verify(interactorMock, times(1)).saveShop(shopData);
        verify(interactorMock, never()).deleteShop(shopData.id);
        verify(shopDetailsViewMock, times(1)).showMessage("Save to bookmark.");
        verify(shopDetailsViewMock, times(1)).setFabImage(R.drawable.ic_turned_in_white_24dp);
        verify(shopDetailsViewMock, never()).setFabImage(R.drawable.ic_turned_in_not_white_24dp);

        when(interactorMock.isShopExists("1")).thenReturn(true);
        presenter.onClick();
        verify(interactorMock, times(1)).deleteShop(shopData.id);
        verify(shopDetailsViewMock, times(1)).showMessage("Deleted from bookmark.");
        verify(shopDetailsViewMock, times(1)).setFabImage(R.drawable.ic_turned_in_white_24dp);
        verify(shopDetailsViewMock, times(1)).setFabImage(R.drawable.ic_turned_in_not_white_24dp);
    }
}
