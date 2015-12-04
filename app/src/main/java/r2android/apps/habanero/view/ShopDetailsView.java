package r2android.apps.habanero.view;

import android.graphics.Bitmap;

import r2android.apps.habanero.domain.model.column.ShopData;

public interface ShopDetailsView {

    void initViews(ShopData shopData);
    void setFabImage(int res);
    void setLargeImageWithPlaceholder(ShopData shopData, Bitmap bitmap);
    void setLargeImage(ShopData shopData);
    void showMessage(String message);
    void upHome();
}
