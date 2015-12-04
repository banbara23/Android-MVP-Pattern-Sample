
package r2android.apps.habanero.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import r2android.apps.habanero.R;
import r2android.apps.habanero.domain.model.column.ShopData;
import r2android.apps.habanero.domain.ShopDetailsUseCase;
import r2android.apps.habanero.presentation.ShopDetailsPresenter;
import r2android.apps.habanero.view.ShopDetailsView;

public class ShopDetailsActivity extends AppCompatActivity implements ShopDetailsView, View.OnClickListener {

    private static final String KEY_SHOP = "key_shop";
    private static final String KEY_BITMAP = "key_bitmap";

    private ShopDetailsPresenter presenter;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.genre)
    TextView genre;
    @InjectView(R.id.access)
    TextView access;
    @InjectView(R.id.icon)
    ImageView icon;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.address)
    TextView address;
    @InjectView(R.id.open)
    TextView open;
    @InjectView(R.id.close)
    TextView close;
    @InjectView(R.id.party_capacity)
    TextView party_capacity;
    @InjectView(R.id.wifi)
    TextView wifi;
    @InjectView(R.id.wedding)
    TextView wedding;
    @InjectView(R.id.course)
    TextView course;
    @InjectView(R.id.free_drink)
    TextView free_drink;
    @InjectView(R.id.free_food)
    TextView free_food;
    @InjectView(R.id.private_room)
    TextView private_room;
    @InjectView(R.id.horigotatsu)
    TextView horigotatsu;
    @InjectView(R.id.tatami)
    TextView tatami;
    @InjectView(R.id.card)
    TextView card;
    @InjectView(R.id.non_smoking)
    TextView non_smoking;
    @InjectView(R.id.charter)
    TextView charter;
    @InjectView(R.id.ktai)
    TextView ktai;
    @InjectView(R.id.parking)
    TextView parking;
    @InjectView(R.id.barrier_free)
    TextView barrier_free;
    @InjectView(R.id.other_memo)
    TextView other_memo;
    @InjectView(R.id.sommelier)
    TextView sommelier;
    @InjectView(R.id.open_air)
    TextView open_air;
    @InjectView(R.id.show)
    TextView show;
    @InjectView(R.id.equipment)
    TextView equipment;
    @InjectView(R.id.karaoke)
    TextView karaoke;
    @InjectView(R.id.band)
    TextView band;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.english)
    TextView english;
    @InjectView(R.id.pet)
    TextView pet;
    @InjectView(R.id.child)
    TextView child;
    @InjectView(R.id.lunch)
    TextView lunch;
    @InjectView(R.id.midnight)
    TextView midnight;
    @InjectView(R.id.shop_detail_memo)
    TextView shop_detail_memo;

    /**
     * インテントの生成
     *
     * @param context 呼び出し元
     * @param shop    店舗情報
     * @param bitmap  画像
     * @return インテント
     */
    public static Intent newInstance(@NonNull Context context, @NonNull ShopData shop, @Nullable Bitmap bitmap) {
        Intent intent = new Intent(context, ShopDetailsActivity.class);
        intent.putExtra(KEY_SHOP, shop);
        if (bitmap != null) {
            intent.putExtra(KEY_BITMAP, bitmap);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        ButterKnife.inject(this);

        if (!getIntent().hasExtra(KEY_SHOP)) {
            Toast.makeText(this, getString(R.string.error_not_selected, "店舗情報"), Toast.LENGTH_SHORT).show();
            supportFinishAfterTransition();
            return;
        }
        Bitmap bitmap = getIntent().getParcelableExtra(KEY_BITMAP);
        presenter = new ShopDetailsPresenter(new ShopDetailsUseCase());
        presenter.onCreate(this, (ShopData) getIntent().getParcelableExtra(KEY_SHOP), bitmap);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onNavigationItemSelected(item.getItemId());
    }

    @Override
    public void onClick(View view) {
        presenter.onClick();
    }

    @Override
    public void initViews(final ShopData shop) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        fab.setOnClickListener(this);

        // 店舗情報の更新
        collapsingToolbar.setTitle(shop.name);
        genre.setText(shop.genre);
        access.setText(shop.access);
        address.setText(shop.address);
        open.setText(shop.open);
        close.setText(shop.close);
        party_capacity.setText(shop.party_capacity);
        wifi.setText(shop.wifi);
        wedding.setText(shop.wedding);
        course.setText(shop.course);
        free_drink.setText(shop.free_drink);
        free_food.setText(shop.free_food);
        private_room.setText(shop.private_room);
        horigotatsu.setText(shop.horigotatsu);
        tatami.setText(shop.tatami);
        card.setText(shop.card);
        non_smoking.setText(shop.non_smoking);
        charter.setText(shop.charter);
        ktai.setText(shop.ktai);
        parking.setText(shop.parking);
        barrier_free.setText(shop.barrier_free);
        other_memo.setText(shop.other_memo);
        sommelier.setText(shop.sommelier);
        open_air.setText(shop.open_air);
        show.setText(shop.show);
        equipment.setText(shop.equipment);
        karaoke.setText(shop.karaoke);
        band.setText(shop.band);
        tv.setText(shop.tv);
        english.setText(shop.english);
        pet.setText(shop.pet);
        child.setText(shop.child);
        lunch.setText(shop.lunch);
        midnight.setText(shop.midnight);
        shop_detail_memo.setText(shop.shop_detail_memo);
    }

    @Override
    public void setFabImage(int res) {
        fab.setImageResource(res);
    }

    @Override
    public void setLargeImageWithPlaceholder(ShopData shop, Bitmap bitmap) {
        icon.setImageBitmap(bitmap);
        Picasso.with(ShopDetailsActivity.this).load(shop.pc_large_image)
                .placeholder(icon.getDrawable())
                .fit().centerCrop()
                .into(icon);

    }

    @Override
    public void setLargeImage(ShopData shop) {
        Picasso.with(ShopDetailsActivity.this).load(shop.pc_large_image)
                .fit().centerCrop()
                .into(icon);
    }

    /**
     * メッセージの表示
     */
    public void showMessage(final String message) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void upHome() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            // 新しくタスクを生成する必要がある
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
            this.supportFinishAfterTransition();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }
}
