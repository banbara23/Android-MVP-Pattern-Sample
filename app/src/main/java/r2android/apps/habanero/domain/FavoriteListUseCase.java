package r2android.apps.habanero.domain;

import java.util.List;

import r2android.apps.habanero.data.FavoriteListRepository;
import r2android.apps.habanero.domain.model.column.ShopData;

public class FavoriteListUseCase {

    private FavoriteListRepository repository;

    public FavoriteListUseCase(FavoriteListRepository repository) {
        this.repository = repository;
    }

    public List<ShopData> getFavoriteList() {
        return repository.getFavoriteList();
    }
}