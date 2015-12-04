package r2android.apps.habanero.domain;

import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.data.storage.ShopDataDao;

public class ShopDetailsUseCase {

    public ShopDetailsUseCase(){}

    public boolean isShopExists(String shopId) {
        return ShopDataDao.isShopExists(shopId);
    }

    public void deleteShop(String shopId) {
        ShopDataDao.deleteShop(shopId);
    }

    public void saveShop(ShopData shopData){
        ShopDataDao.saveShop(shopData);
    }
}
