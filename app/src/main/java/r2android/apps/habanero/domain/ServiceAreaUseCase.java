package r2android.apps.habanero.domain;

import android.support.annotation.NonNull;

import r2android.apps.habanero.data.ServiceAreaRepository;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaRoot;
import r2android.apps.habanero.domain.common.listener.OnFinishedListener;

public class ServiceAreaUseCase {

    private ServiceAreaRepository repository;

    public ServiceAreaUseCase(ServiceAreaRepository repository) {
        this.repository = repository;
    }

    public void getServiceArea(@NonNull final OnFinishedListener<ServiceAreaRoot> listener) {
        repository.getServiceArea(listener);
    }
}
