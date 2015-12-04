package r2android.apps.habanero.data;

import android.content.Context;

import r2android.apps.habanero.data.network.RetroFitUtil;
import r2android.apps.habanero.data.network.ServiceAreaApi;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaRoot;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ServiceAreaRepository {

    private Context context;

    public ServiceAreaRepository(Context context) {
        this.context = context;
    }

    public void getServiceArea(final OnFinishedListener<ServiceAreaRoot> listener) {
        ServiceAreaApi serviceAreaApi = RetroFitUtil.createRestAdapterBuilder(context).build().create(ServiceAreaApi.class);
        serviceAreaApi.getServiceArea(new Callback<ServiceAreaRoot>() {

            @Override
            public void success(ServiceAreaRoot serviceArea, Response response) {
                listener.onFinished(serviceArea);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                retrofitError.printStackTrace();
                listener.onFinished(null);
            }
        });
    }

}
