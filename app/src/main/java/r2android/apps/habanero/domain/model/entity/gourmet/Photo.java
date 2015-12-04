
package r2android.apps.habanero.domain.model.entity.gourmet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chihiro Koyama on 15/04/08.
 */
public class Photo {

    @SerializedName("pc")
    public PcPhoto pc;
    @SerializedName("mobile")
    public MobilePhoto mobile;
}
