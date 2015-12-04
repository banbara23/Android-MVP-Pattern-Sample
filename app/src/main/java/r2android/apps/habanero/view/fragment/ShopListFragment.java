package r2android.apps.habanero.view.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import r2android.apps.habanero.R;
import r2android.apps.habanero.data.ShopListRepository;
import r2android.apps.habanero.domain.model.entity.servicearea.ServiceArea;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.ShopListUseCase;
import r2android.apps.habanero.presentation.ShopListPresenter;
import r2android.apps.habanero.view.ShopListView;
import r2android.apps.habanero.view.activity.ShopDetailsActivity;
import r2android.apps.habanero.view.adapter.ShopListAdapter;

public class ShopListFragment extends ListFragment implements ShopListView, AbsListView.OnScrollListener {

    private static final String KEY_SERVICE_AREA_CODE = "key_servcie_area_code";
    private static final String KEY_SERVICE_AREA_NAME = "key_servcie_area_name";

    private ShopListPresenter presenter;

    private ShopListAdapter adapter;
    private View footer;

    public static ShopListFragment newInstance(@NonNull ServiceArea area) {
        ShopListFragment fragment = new ShopListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SERVICE_AREA_CODE, area.code);
        bundle.putString(KEY_SERVICE_AREA_NAME, area.name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new ShopListPresenter(new ShopListUseCase(new ShopListRepository(getActivity().getApplicationContext())));
        String serviceArea = getArguments().getString(KEY_SERVICE_AREA_CODE);
        if (serviceArea == null) {
            return;
        }
        presenter.onCreate(this, serviceArea);
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        presenter.onListItemClick(getItem(position), (ImageView) v.findViewById(R.id.icon));

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        presenter.onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_shop_list, getArguments().getString(KEY_SERVICE_AREA_NAME)));
        footer = View.inflate(getActivity(), R.layout.footer_loading, null);
        getListView().addFooterView(footer, null, false);
        getListView().setOnScrollListener(this);
        adapter = new ShopListAdapter(getActivity().getApplication(), presenter);
        setListAdapter(adapter);
    }

    @Override
    public void addShopList(List<ShopData> shopList) {
        adapter.addAll(shopList);
    }

    @Override
    public void removeFooter() {
        getListView().removeFooterView(footer);
    }

    @Override
    public ShopData getItem(int position) {
        return adapter.getItem(position);
    }

    @Override
    public int getFooterViewsCount() {
        return getListView().getFooterViewsCount();
    }

    @Override
    public void startShopDetailsActivity(ShopData shopData, Bitmap bitmap) {
        Intent intent = ShopDetailsActivity.newInstance(getActivity(), shopData, bitmap);
        ActivityCompat.startActivity(getActivity(), intent, null);
    }

    @Override
    public void initListViewCell(ShopListAdapter.ViewHolder holder, ShopData shop) {
        holder.genre.setText(shop.genre);
        holder.name.setText(shop.name);
        holder.access.setText(shop.mobile_access);

        Picasso.with(getActivity().getApplicationContext()).load(shop.pc_small_image).fit().centerCrop().into(holder.icon);
    }

    @Override
    public void setGenreVisibility(ShopListAdapter.ViewHolder holder, int visibility) {
        holder.genre.setVisibility(visibility);
    }
}