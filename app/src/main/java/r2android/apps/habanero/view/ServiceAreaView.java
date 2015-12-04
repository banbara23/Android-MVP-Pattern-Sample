package r2android.apps.habanero.view;

import java.util.List;

import r2android.apps.habanero.domain.model.entity.servicearea.ServiceArea;

public interface ServiceAreaView {

    void initViews();
    void showShopList(ServiceArea area);
    void updateListView(List<ServiceArea> area);
}
