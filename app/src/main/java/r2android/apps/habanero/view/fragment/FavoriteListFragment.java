
package r2android.apps.habanero.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import r2android.apps.habanero.R;
import r2android.apps.habanero.data.FavoriteListRepository;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.FavoriteListUseCase;
import r2android.apps.habanero.presentation.FavoriteListPresenter;
import r2android.apps.habanero.view.FavoriteListView;
import r2android.apps.habanero.view.activity.ShopDetailsActivity;
import r2android.apps.habanero.view.adapter.FavoriteListAdapter;

public class FavoriteListFragment extends ListFragment implements FavoriteListView {

    private FavoriteListPresenter presenter;
    private FavoriteListAdapter adapter;

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new FavoriteListPresenter(new FavoriteListUseCase(new FavoriteListRepository()));
        presenter.onCreate(this);
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        presenter.onListItemClick(adapter.getItem(position), (ImageView) v.findViewById(R.id.icon));
    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_favorite_list);
        setEmptyText(getString(R.string.empty_favorite_list));
        adapter = new FavoriteListAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void addShopList(List<ShopData> shopList) {
        adapter.addAll(shopList);
    }

    @Override
    public void startShopDetailsActivity(ShopData shopData, Bitmap bitmap) {
        Intent intent = ShopDetailsActivity.newInstance(getActivity(), shopData, bitmap);
        ActivityCompat.startActivity(getActivity(), intent, null);
    }
}
