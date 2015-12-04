
package r2android.apps.habanero.presentation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import r2android.apps.habanero.domain.model.entity.gourmet.Shop;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;
import r2android.apps.habanero.domain.ShopListUseCase;
import r2android.apps.habanero.view.ShopListView;
import r2android.apps.habanero.view.adapter.ShopListAdapter;

public class ShopListPresenter implements OnFinishedListener<GourmetRoot> {

    private ShopListUseCase useCase;
    private ShopListView view;

    private int start = 1;
    private String serviceAreaCode;

    public ShopListPresenter(ShopListUseCase useCase) {
        this.useCase = useCase;
    }

    public void onCreate(@NonNull ShopListView shopListView, @NonNull String serviceAreaCode) {
        this.view = shopListView;
        this.serviceAreaCode = serviceAreaCode;

        view.initViews();
        useCase.getShopListByServiceArea(serviceAreaCode, start, this);
    }

    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onFinished(GourmetRoot gourmetResult) {
        if (view == null) return;

        if (gourmetResult.results.results_available <= gourmetResult.results.results_returned + gourmetResult.results.results_start - 1) {
            view.removeFooter();
        }

        List<Shop> serviceAreaList = gourmetResult.results.shop;
        List<ShopData> shopList = new ArrayList<>();
        for (Shop shop : serviceAreaList) {
            shopList.add(new ShopData(shop));
        }
        view.addShopList(shopList);
        start += gourmetResult.results.results_returned;
    }


    public void onListItemClick(ShopData shop, ImageView iconView) {
        if (view == null) return;

        Bitmap bitmap = null;
        if (iconView.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) iconView.getDrawable()).getBitmap();
        }
        view.startShopDetailsActivity(shop, bitmap);
    }

    public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view == null) return;

        boolean isLastItemVisible = totalItemCount == firstVisibleItem + visibleItemCount;
        if (isLastItemVisible && view.getFooterViewsCount() > 0) {
            useCase.getShopListByServiceArea(serviceAreaCode, start, this);
        }
    }

    public void onGetView(ShopListAdapter.ViewHolder holder, ShopData shop) {
        view.initListViewCell(holder, shop);
        if (shop.genre == null) {
            view.setGenreVisibility(holder, View.INVISIBLE);
        } else {
            view.setGenreVisibility(holder, View.VISIBLE);
        }
    }
}
