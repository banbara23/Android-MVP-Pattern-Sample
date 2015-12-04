package r2android.apps.habanero.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import r2android.apps.habanero.R;
import r2android.apps.habanero.data.ServiceAreaRepository;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceArea;
import r2android.apps.habanero.domain.ServiceAreaUseCase;
import r2android.apps.habanero.presentation.ServiceAreaPresenter;
import r2android.apps.habanero.view.ServiceAreaView;

public class ServiceAreaFragment extends ListFragment implements ServiceAreaView {

    private ServiceAreaPresenter presenter;

    public static ServiceAreaFragment newInstance() {
        return new ServiceAreaFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new ServiceAreaPresenter(new ServiceAreaUseCase(new ServiceAreaRepository(getActivity().getApplicationContext())));
        presenter.onCreate(this);
    }

    @Override
    public void initViews(){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_area_list);
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        presenter.onListItemClick((ServiceArea) getListAdapter().getItem(position));
    }

    @Override
    public void showShopList(ServiceArea area) {
        ShopListFragment fragment = ShopListFragment.newInstance(area);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(ShopListFragment.class.getName())
                .commit();

//        ((MainActivity) getActivity()).showShopList(area);
    }

    public void updateListView(final List<ServiceArea> list) {
        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_service_area, R.id.text, list));
    }
}