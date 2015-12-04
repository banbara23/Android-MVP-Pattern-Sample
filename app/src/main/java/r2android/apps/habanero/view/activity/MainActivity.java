
package r2android.apps.habanero.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import r2android.apps.habanero.R;
import r2android.apps.habanero.presentation.MainPresenter;
import r2android.apps.habanero.view.MainView;
import r2android.apps.habanero.view.fragment.FavoriteListFragment;
import r2android.apps.habanero.view.fragment.ServiceAreaFragment;

/**
 * メインアクティビティ
 */
public class MainActivity extends AppCompatActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, ServiceAreaFragment.newInstance(), "main")
                        .commit();
            }
        }
        presenter = new MainPresenter();
        presenter.onCreate(this);
    }

    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return presenter.onNavigationItemSelected(menuItem.getItemId());
    }

    @Override
    public void initViews() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        toolbar.setAlpha(1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void openDrawers(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void showServiceAreaList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, ServiceAreaFragment.newInstance(), "main").commit();
    }

    @Override
    public void showFavoriteList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, FavoriteListFragment.newInstance(), "main").commit();
    }

    @Override
    public void popBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
