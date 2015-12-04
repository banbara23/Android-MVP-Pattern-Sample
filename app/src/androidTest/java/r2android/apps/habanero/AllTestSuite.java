package r2android.apps.habanero;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import r2android.apps.habanero.favoritelist.FavoriteListTestSuite;
import r2android.apps.habanero.main.MainTestSuite;
import r2android.apps.habanero.shopdetails.ShopDetailsTestSuite;
import r2android.apps.habanero.shoplist.ShopListTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MainTestSuite.class, ShopListTestSuite.class, FavoriteListTestSuite.class, ShopDetailsTestSuite.class})
public class AllTestSuite {
}