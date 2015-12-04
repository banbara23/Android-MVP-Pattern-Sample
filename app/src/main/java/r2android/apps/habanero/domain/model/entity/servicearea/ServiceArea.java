
package r2android.apps.habanero.domain.model.entity.servicearea;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Chihiro Koyama on 15/04/06.
 */
public class ServiceArea implements Serializable{
    @SerializedName("code")
    public String code;
    @SerializedName("name")
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
