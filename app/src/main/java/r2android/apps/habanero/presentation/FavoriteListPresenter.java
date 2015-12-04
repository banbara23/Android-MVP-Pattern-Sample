
package r2android.apps.habanero.presentation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.FavoriteListUseCase;
import r2android.apps.habanero.view.FavoriteListView;

public class FavoriteListPresenter {

    private FavoriteListView view;
    private FavoriteListUseCase useCase;

    public FavoriteListPresenter(FavoriteListUseCase favoriteListUseCase) {
        useCase = favoriteListUseCase;
    }

    public void onCreate(@NonNull FavoriteListView favoriteListView) {
        view = favoriteListView;
        view.initViews();
        view.addShopList(useCase.getFavoriteList());
    }

    public void onDestroy() {
        this.view = null;
    }

    public void onListItemClick(ShopData shop, ImageView imageView) {
        if (view == null) return;

        Bitmap bitmap = null;
        if (imageView.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        }
        view.startShopDetailsActivity(shop, bitmap);
    }
}
