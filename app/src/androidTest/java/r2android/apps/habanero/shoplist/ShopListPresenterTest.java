package r2android.apps.habanero.shoplist;

import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import r2android.apps.habanero.domain.model.entity.gourmet.Genre;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetResult;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import r2android.apps.habanero.domain.model.entity.gourmet.MobilePhoto;
import r2android.apps.habanero.domain.model.entity.gourmet.PcPhoto;
import r2android.apps.habanero.domain.model.entity.gourmet.Photo;
import r2android.apps.habanero.domain.model.entity.gourmet.Shop;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.ShopListUseCase;
import r2android.apps.habanero.presentation.ShopListPresenter;
import r2android.apps.habanero.view.ShopListView;
import r2android.apps.habanero.view.adapter.ShopListAdapter;

@RunWith(AndroidJUnit4.class)
public class ShopListPresenterTest {

    private ShopListUseCase useCaseMock;
    private ShopListView viewMock;

    private GourmetRoot gourmetRoot;

    @Before
    public void setup() {
        useCaseMock = mock(ShopListUseCase.class);
        viewMock = mock(ShopListView.class);

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
    public void onCreateTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");
        verify(viewMock, times(1)).initViews();
        verify(useCaseMock, times(1)).getShopListByServiceArea("01", 1, presenter);
    }

    @Test
    public void onFinishedTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(1)).addShopList(any(List.class));
        verify(viewMock, never()).removeFooter();
    }

    @Test
    public void removeFooterTest() {
        // 現在の取得数がavailableより少なければremoveFooterは呼ばれない
        gourmetRoot.results.results_available = 21;
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        verify(viewMock, never()).removeFooter();

        // 現在の取得数がavailableと同じならremoveFooterが呼ばれる
        gourmetRoot.results.results_available = 20;
        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(1)).removeFooter();
    }

    @Test
    public void onScrollTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        when(viewMock.getFooterViewsCount()).thenReturn(1);

        // firstVisibleItem + visibleItemCountがtotalItemCountと同じになるまでは更読みリクエストは呼ばれない
        presenter.onScroll(0, 0, 20);
        presenter.onScroll(1, 2, 20);
        presenter.onScroll(10, 3, 20);
        presenter.onScroll(14, 3, 20);
        presenter.onScroll(15, 3, 20);
        presenter.onScroll(16, 3, 20);
        verify(useCaseMock, times(1)).getShopListByServiceArea(anyString(), anyInt(), any(ShopListPresenter.class));
        verify(viewMock, times(1)).addShopList(any(List.class));

        // firstVisibleItem + visibleItemCountがtotalItemCountと同じになれば呼ばれる
        presenter.onScroll(17, 3, 20);
        verify(useCaseMock, times(2)).getShopListByServiceArea(anyString(), anyInt(), any(ShopListPresenter.class));

        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(2)).addShopList(any(List.class));
    }

    @Test
    public void onListItemClickTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");

        // IconのImageViewにBitmapが設定されていなければBitmapはnullで画面遷移が呼ばれる
        ShopData shopData = new ShopData(gourmetRoot.results.shop.get(0));
        ImageView imageView = new ImageView(InstrumentationRegistry.getContext());
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);

        // IconのImageViewにBitmapが設定されていればImageViewのBitmapを引数に画面遷移が呼ばれる
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        imageView.setImageBitmap(bitmap);
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, bitmap);
    }

    @Test
    public void onGetViewTest() {
        ShopData shop = new ShopData();
        ShopListAdapter.ViewHolder holder = mock(ShopListAdapter.ViewHolder.class);
        shop.genre = null;

        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.onCreate(viewMock, "01");
        presenter.onGetView(holder, shop);
        verify(viewMock, times(1)).initListViewCell(holder, shop);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.INVISIBLE);
        verify(viewMock, never()).setGenreVisibility(holder, View.VISIBLE);

        shop.genre = "ジャンル";
        presenter.onGetView(holder, shop);
        verify(viewMock, times(2)).initListViewCell(holder, shop);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.INVISIBLE);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.VISIBLE);
    }
}
