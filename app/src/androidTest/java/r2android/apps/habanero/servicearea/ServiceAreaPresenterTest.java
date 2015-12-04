package r2android.apps.habanero.servicearea;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import r2android.apps.habanero.domain.model.entity.servicearea.ServiceArea;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaResult;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceAreaRoot;
import r2android.apps.habanero.domain.ServiceAreaUseCase;
import r2android.apps.habanero.presentation.ServiceAreaPresenter;
import r2android.apps.habanero.view.ServiceAreaView;

import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class ServiceAreaPresenterTest {

    private ServiceAreaView viewMock;
    private ServiceAreaUseCase useCaseMock;
    private ServiceAreaRoot serviceAreaRoot;

    @Before
    public void setup() {
        viewMock = mock(ServiceAreaView.class);
        useCaseMock = mock(ServiceAreaUseCase.class);

        serviceAreaRoot = new ServiceAreaRoot();
        serviceAreaRoot.results = new ServiceAreaResult();
        serviceAreaRoot.results.service_area = new ArrayList<>();
        serviceAreaRoot.results.service_area.add(new ServiceArea());
    }

    @Test
    public void onCreateTest() {
        ServiceAreaPresenter presenter = new ServiceAreaPresenter(useCaseMock);
        presenter.onCreate(viewMock);
        verify(viewMock, times(1)).initViews();
        verify(useCaseMock, times(1)).getServiceArea(presenter);
    }

    @Test
    public void onFinishedTest() {
        ServiceAreaPresenter presenter = new ServiceAreaPresenter(useCaseMock);
        presenter.onCreate(viewMock);
        presenter.onFinished(serviceAreaRoot);
        verify(viewMock, times(1)).updateListView(serviceAreaRoot.results.service_area);
    }

    @Test
    public void onListItemClickTest() {
        ServiceAreaPresenter presenter = new ServiceAreaPresenter(useCaseMock);
        presenter.onCreate(viewMock);
        presenter.onFinished(serviceAreaRoot);
        presenter.onListItemClick(serviceAreaRoot.results.service_area.get(0));
        verify(viewMock, times(1)).showShopList(serviceAreaRoot.results.service_area.get(0));
    }
}
