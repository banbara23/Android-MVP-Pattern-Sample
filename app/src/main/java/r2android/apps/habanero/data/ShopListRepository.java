package r2android.apps.habanero.data;

import android.content.Context;

import r2android.apps.habanero.data.network.GourmetApi;
import r2android.apps.habanero.data.network.RetroFitUtil;
import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShopListRepository {

    private Context context;

    public ShopListRepository(Context context) {
        this.context = context;
    }

    public void getShopListByServiceArea(String key, int start, final OnFinishedListener<GourmetRoot> listener) {
        GourmetApi gourmetApi = RetroFitUtil.createRestAdapterBuilder(context).build().create(GourmetApi.class);
        gourmetApi.getShopListByServiceArea(key, start, new Callback<GourmetRoot>() {
            @Override
            public void success(GourmetRoot gourmetRoot, Response response) {
                listener.onFinished(gourmetRoot);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}
