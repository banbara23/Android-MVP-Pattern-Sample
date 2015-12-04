
package r2android.apps.habanero.domain;

import android.support.annotation.NonNull;

import r2android.apps.habanero.data.ShopListRepository;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;

public class ShopListUseCase {

    private ShopListRepository repository;

    public ShopListUseCase(ShopListRepository repository) {
        this.repository = repository;
    }

    public void getShopListByServiceArea(@NonNull String key, int start, @NonNull final OnFinishedListener<GourmetRoot> listener) {
        repository.getShopListByServiceArea(key, start, listener);
    }
}
