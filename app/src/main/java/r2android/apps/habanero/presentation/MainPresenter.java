
package r2android.apps.habanero.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import r2android.apps.habanero.R;
import r2android.apps.habanero.view.MainView;

public class MainPresenter {

    @Nullable
    private MainView view;

    public void onCreate(@NonNull MainView mainView) {
        view = mainView;
        view.initViews();
    }

    public void onDestroy() {
        view = null;
    }

    public boolean onOptionsItemSelected(int itemId) {
        if (view == null) return false;

        switch (itemId) {
            case android.R.id.home:
                view.openDrawers();
                return true;
        }
        return false;
    }

    public boolean onNavigationItemSelected(int itemId) {
        if (view == null) return false;

        view.closeDrawers();
        switch (itemId) {
            case R.id.search_from_area:
                view.popBackStack();
                view.showServiceAreaList();
                return true;
            case R.id.select_from_bookmark:
                view.popBackStack();
                view.showFavoriteList();
                return true;
            default:
                return false;
        }
    }
}
