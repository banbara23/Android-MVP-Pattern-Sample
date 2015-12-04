
package r2android.apps.habanero.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import r2android.apps.habanero.R;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.presentation.ShopListPresenter;

public class ShopListAdapter extends ArrayAdapter<ShopData> {

    private ShopListPresenter presenter;
    private LayoutInflater inflater;

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     */
    public ShopListAdapter(Context context, ShopListPresenter shopListPresenter) {
        super(context, R.layout.item_shop);
        inflater = LayoutInflater.from(context);
        presenter = shopListPresenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_shop, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        presenter.onGetView(holder, getItem(position));
        return convertView;
    }

    public static class ViewHolder {
        @InjectView(R.id.genre)
        public TextView genre;
        @InjectView(R.id.name)
        public TextView name;
        @InjectView(R.id.access)
        public TextView access;
        @InjectView(R.id.icon)
        public ImageView icon;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
