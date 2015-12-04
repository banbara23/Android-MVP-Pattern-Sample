package r2android.apps.habanero.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import r2android.apps.habanero.R;
import r2android.apps.habanero.domain.model.column.ShopData;

public class FavoriteListAdapter extends ArrayAdapter<ShopData> {

    private LayoutInflater inflater;

    /**
     * コンストラクタ
     *
     * @param context      コンテキスト
     */
    public FavoriteListAdapter(Context context) {
        super(context, R.layout.item_shop);
        inflater = LayoutInflater.from(context);
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

        ShopData shop = getItem(position);
        holder.genre.setText(shop.genre);
        holder.name.setText(shop.name);
        holder.access.setText(shop.mobile_access);

        Picasso.with(getContext()).load(shop.pc_small_image).fit().centerCrop().into(holder.icon);
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
