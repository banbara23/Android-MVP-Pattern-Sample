
package r2android.apps.habanero.data.network;

import r2android.apps.habanero.R;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * Created by Chihiro Koyama on 15/04/06.
 */
public class RetroFitUtil {

    /**
     * APIリクエスト
     *
     * @param context コンテキスト
     * @return リクエストビルダー
     */
    public static RestAdapter.Builder createRestAdapterBuilder(@NonNull
    final Context context) {

        // APIキーなどの基本情報を付与
        return new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.endpoint))
                .setConverter(new GsonConverter(new Gson()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addQueryParam("key", context.getString(R.string.key));
                        request.addQueryParam("format", "json");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL);
    }

}
