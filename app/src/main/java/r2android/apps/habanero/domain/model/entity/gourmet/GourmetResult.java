
package r2android.apps.habanero.domain.model.entity.gourmet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chihiro Koyama on 15/04/06.
 */
public class GourmetResult {

    @SerializedName("api_version")
    public String api_version;
    @SerializedName("results_available")
    public int results_available;
    @SerializedName("results_returned")
    public int results_returned;
    @SerializedName("results_start")
    public int results_start;

    @SerializedName("shop")
    public List<Shop> shop;

}
