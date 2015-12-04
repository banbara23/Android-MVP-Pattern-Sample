package r2android.apps.habanero.data;

import com.activeandroid.query.Select;

import java.util.List;

import r2android.apps.habanero.domain.model.column.ShopData;

public class FavoriteListRepository {

    public List<ShopData> getFavoriteList() {
        return new Select().from(ShopData.class).execute();
    }
}
