
package r2android.apps.habanero.presentation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import r2android.apps.habanero.R;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.ShopDetailsUseCase;
import r2android.apps.habanero.view.ShopDetailsView;

public class ShopDetailsPresenter {

    private ShopDetailsView view;
    private ShopDetailsUseCase useCase;

    private ShopData model;

    public ShopDetailsPresenter(ShopDetailsUseCase shopDetailsUseCase){
        this.useCase = shopDetailsUseCase;
    }

    public void onCreate(@NonNull ShopDetailsView shopDetailsView, ShopData shop, Bitmap bitmap) {
        view = shopDetailsView;
        model = shop;

        view.initViews(shop);
        if (useCase.isShopExists(model.id)) {
            view.setFabImage(R.drawable.ic_turned_in_white_24dp);
        }
        if (bitmap != null) {
            view.setLargeImageWithPlaceholder(shop, bitmap);
        } else {
            view.setLargeImage(shop);
        }
    }

    public void onDestroy() {
        this.view = null;
    }

    public boolean onNavigationItemSelected(int itemId) {
        if (view == null) return false;

        switch (itemId) {
            case android.R.id.home:
                view.upHome();
                return true;
        }
        return false;
    }

    public void onClick() {
        if (view == null) return;

        String message;
        int resId;
        if (useCase.isShopExists(model.id)) {
            useCase.deleteShop(model.id);
            resId = R.drawable.ic_turned_in_not_white_24dp;
            message = "Deleted from bookmark.";
        } else {
            useCase.saveShop(model);
            resId = R.drawable.ic_turned_in_white_24dp;
            message = "Save to bookmark.";
        }
        view.setFabImage(resId);
        view.showMessage(message);
    }
}
