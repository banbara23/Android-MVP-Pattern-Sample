
package r2android.apps.habanero.data.network;

import r2android.apps.habanero.domain.model.entity.gourmet.GourmetRoot;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Chihiro Koyama on 15/04/15.
 */
public interface GourmetApi {

    /**
     * 店舗一覧取得
     *
     * @param serviceAreaCode サービスエリアコード
     * @param start 開始位置
     * @param cb コールバック
     */
    @GET("/gourmet/v1/")
    void getShopListByServiceArea(@Query("service_area") String serviceAreaCode,
            @Query("start") int start, Callback<GourmetRoot> cb);
}
