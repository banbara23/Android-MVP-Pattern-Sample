
package r2android.apps.habanero.data.network;

import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaRoot;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Chihiro Koyama on 15/04/15.
 */
public interface ServiceAreaApi {

    /**
     * サービスエリア一覧取得
     *
     * @param cb コールバック
     */
    @GET("/service_area/v1/")
    void getServiceArea(Callback<ServiceAreaRoot> cb);
}
