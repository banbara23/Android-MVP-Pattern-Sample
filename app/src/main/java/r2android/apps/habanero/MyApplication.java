
package r2android.apps.habanero;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Chihiro Koyama on 15/04/09.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
