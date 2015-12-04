package r2android.apps.habanero.favoritelist;

import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import r2android.apps.habanero.domain.model.entity.gourmet.Genre;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetResult;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import r2android.apps.habanero.domain.model.entity.gourmet.MobilePhoto;
import r2android.apps.habanero.domain.model.entity.gourmet.PcPhoto;
import r2android.apps.habanero.domain.model.entity.gourmet.Photo;
import r2android.apps.habanero.domain.model.entity.gourmet.Shop;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.FavoriteListUseCase;
import r2android.apps.habanero.presentation.FavoriteListPresenter;
import r2android.apps.habanero.view.FavoriteListView;

import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class FavoriteListPresenterTest {

    private FavoriteListView viewMock;
    private FavoriteListUseCase useCaseMock;
    private GourmetRoot gourmetRoot;

    @Before
    public void setup(){
        viewMock = mock(FavoriteListView.class);
        useCaseMock = mock(FavoriteListUseCase.class);

        gourmetRoot = new GourmetRoot();
        gourmetRoot.results = new GourmetResult();
        gourmetRoot.results.results_available = 100;
        gourmetRoot.results.results_returned = 20;
        gourmetRoot.results.results_start = 1;
        gourmetRoot.results.shop = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Shop shop = new Shop();
            shop.id = i + "";
            shop.name = "name:" + i;
            shop.access = "access:" + i;
            shop.photo = new Photo();
            shop.photo.pc = new PcPhoto();
            shop.photo.mobile = new MobilePhoto();
            shop.genre = new Genre();
            gourmetRoot.results.shop.add(shop);
        }
    }

    @Test
    public void onCreateTest(){
        List<ShopData> shopList = new ArrayList<ShopData>();
        when(useCaseMock.getFavoriteList()).thenReturn(shopList);

        FavoriteListPresenter presenter = new FavoriteListPresenter(useCaseMock);
        presenter.onCreate(viewMock);
        verify(viewMock, times(1)).initViews();
        verify(useCaseMock, times(1)).getFavoriteList();
        verify(viewMock, times(1)).addShopList(shopList);
    }

    @Test
    public void onListItemClickTest() {
        FavoriteListPresenter presenter = new FavoriteListPresenter(useCaseMock);
        presenter.onCreate(viewMock);

        ShopData shopData = new ShopData(gourmetRoot.results.shop.get(0));
        ImageView imageView = new ImageView(InstrumentationRegistry.getContext());
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);

        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        imageView.setImageBitmap(bitmap);
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, bitmap);
    }
}
