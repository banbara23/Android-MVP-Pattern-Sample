
package r2android.apps.habanero.domain.model.entity.gourmet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chihiro Koyama on 15/04/07.
 */
public class Shop {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("logo_image")
    public String logo_image;
    @SerializedName("name_kana")
    public String name_kana;
    @SerializedName("address")
    public String address;
    @SerializedName("station_name")
    public String station_name;
    @SerializedName("ktai_coupon")
    public int ktai_coupon;
    @SerializedName("lat")
    public float lat;
    @SerializedName("lng")
    public float lng;

    @SerializedName("access")
    public String access;
    @SerializedName("mobile_access")
    public String mobile_access;

    @SerializedName("open")
    public String open;
    @SerializedName("close")
    public String close;
    @SerializedName("party_capacity")
    public String party_capacity;
    @SerializedName("wifi")
    public String wifi;
    @SerializedName("wedding")
    public String wedding;
    @SerializedName("course")
    public String course;
    @SerializedName("free_drink")
    public String free_drink;
    @SerializedName("free_food")
    public String free_food;
    @SerializedName("private_room")
    public String private_room;
    @SerializedName("horigotatsu")
    public String horigotatsu;
    @SerializedName("tatami")
    public String tatami;
    @SerializedName("card")
    public String card;
    @SerializedName("non_smoking")
    public String non_smoking;
    @SerializedName("charter")
    public String charter;
    @SerializedName("ktai")
    public String ktai;
    @SerializedName("parking")
    public String parking;
    @SerializedName("barrier_free")
    public String barrier_free;
    @SerializedName("other_memo")
    public String other_memo;
    @SerializedName("sommelier")
    public String sommelier;
    @SerializedName("open_air")
    public String open_air;
    @SerializedName("show")
    public String show;
    @SerializedName("equipment")
    public String equipment;
    @SerializedName("karaoke")
    public String karaoke;
    @SerializedName("band")
    public String band;
    @SerializedName("tv")
    public String tv;
    @SerializedName("english")
    public String english;
    @SerializedName("pet")
    public String pet;
    @SerializedName("child")
    public String child;
    @SerializedName("lunch")
    public String lunch;
    @SerializedName("midnight")
    public String midnight;
    @SerializedName("shop_detail_memo")
    public String shop_detail_memo;

    @SerializedName("photo")
    public Photo photo;

    @SerializedName("genre")
    public Genre genre;

    @Override
    public String toString() {
        return name;
    }
}
