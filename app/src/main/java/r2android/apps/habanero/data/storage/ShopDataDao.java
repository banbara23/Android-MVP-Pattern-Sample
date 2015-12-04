package r2android.apps.habanero.data.storage;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import r2android.apps.habanero.domain.model.column.ShopData;

public class ShopDataDao {

    public static boolean isShopExists(String shopId) {
        return new Select().from(ShopData.class).where("shop_id = ?", shopId).exists();
    }

    public static void deleteShop(String shopId) {
        new Delete().from(ShopData.class).where("shop_id = ?", shopId).execute();
    }

    public static void saveShop(ShopData shopData) {
        shopData.save();
    }
}
