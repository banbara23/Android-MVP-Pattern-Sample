package r2android.apps.habanero.view;

import android.graphics.Bitmap;

import java.util.List;

import r2android.apps.habanero.domain.model.column.ShopData;

public interface FavoriteListView {

    void initViews();
    void addShopList(List<ShopData> shopList);
    void startShopDetailsActivity(ShopData shopData, Bitmap bitmap);
}
