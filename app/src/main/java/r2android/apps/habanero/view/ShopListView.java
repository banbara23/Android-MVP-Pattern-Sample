package r2android.apps.habanero.view;

import android.graphics.Bitmap;

import java.util.List;

import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.view.adapter.ShopListAdapter;

public interface ShopListView {

    void initViews();
    void addShopList(List<ShopData> shopList);
    void removeFooter();
    void startShopDetailsActivity(ShopData shopData, Bitmap bitmap);
    ShopData getItem(int position);
    int getFooterViewsCount();
    void initListViewCell(ShopListAdapter.ViewHolder holder, ShopData shop);
    void setGenreVisibility(ShopListAdapter.ViewHolder holder, int visibility);
}
