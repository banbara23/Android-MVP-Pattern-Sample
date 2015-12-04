package r2android.apps.habanero.presentation;

import android.support.annotation.NonNull;

import r2android.apps.habanero.domain.model.entity.servicearea.ServiceArea;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaRoot;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;
import r2android.apps.habanero.domain.ServiceAreaUseCase;
import r2android.apps.habanero.view.ServiceAreaView;

public class ServiceAreaPresenter implements OnFinishedListener<ServiceAreaRoot> {

    private ServiceAreaView view;
    private ServiceAreaUseCase useCase;

    public ServiceAreaPresenter(ServiceAreaUseCase serviceAreaUseCase) {
        this.useCase = serviceAreaUseCase;
    }

    public void onCreate(@NonNull ServiceAreaView serviceAreaView) {
        this.view = serviceAreaView;
        view.initViews();
        useCase.getServiceArea(this);
    }

    public void onDestroy() {
        this.view = null;
    }

    public void onListItemClick(ServiceArea serviceArea) {
        if (view == null) return;
        view.showShopList(serviceArea);
    }

    @Override
    public void onFinished(ServiceAreaRoot serviceAreaResult) {
        if (view == null) return;
        view.updateListView(serviceAreaResult.results.service_area);
    }
}
