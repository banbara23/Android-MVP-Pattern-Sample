
package r2android.apps.habanero.domain.model.column;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import r2android.apps.habanero.domain.model.entity.gourmet.Shop;

/**
 * Created by Chihiro Koyama on 15/04/08.
 */
@Table(name = "shop")
public class ShopData extends Model implements Parcelable, Cloneable {
    public static final Creator<ShopData> CREATOR = new Creator<ShopData>() {
        public ShopData createFromParcel(Parcel source) {
            return new ShopData(source);
        }

        public ShopData[] newArray(int size) {
            return new ShopData[size];
        }
    };
    // お店ID
    @Column(name = "shop_id", unique = true)
    public String id;
    // 掲載店名
    @Column(name = "name")
    public String name;
    // 掲載店名かな
    @Column(name = "name_kana")
    public String name_kana;
    // ロゴ画像
    @Column(name = "logo_image")
    public String logo_image;
    // 住所
    @Column(name = "address")
    public String address;
    // 緯度
    @Column(name = "lat")
    public float lat;
    // 軽度
    @Column(name = "lng")
    public float lng;
    // PC向け画像L
    @Column(name = "pc_large_image")
    public String pc_large_image;
    // PC向け画像M
    @Column(name = "pc_medium_image")
    public String pc_medium_image;
    // PC向け画像S
    @Column(name = "pc_small_image")
    public String pc_small_image;
    // モバイル向け画像L
    @Column(name = "mobile_large_image")
    public String mobile_large_image;
    // モバイル向け画像S
    @Column(name = "mobile_small_image")
    public String mobile_small_image;
    // アクセス
    @Column(name = "access")
    public String access;
    // モバイルアクセス
    @Column(name = "mobile_access")
    public String mobile_access;
    // ジャンル
    @Column(name = "genre")
    public String genre;
    // 営業時間
    @Column(name = "open")
    public String open;
    // 定休日
    @Column(name = "close")
    public String close;
    // 最大宴会収容人数
    @Column(name = "party_capacity")
    public String party_capacity;
    // WiFi 有無
    @Column(name = "wifi")
    public String wifi;
    // ウェディング･二次会
    @Column(name = "wedding")
    public String wedding;
    // コース
    @Column(name = "course")
    public String course;
    // 飲み放題
    @Column(name = "free_drink")
    public String free_drink;
    // 食べ放題
    @Column(name = "free_food")
    public String free_food;
    // 個室
    @Column(name = "private_room")
    public String private_room;
    // 掘りごたつ
    @Column(name = "horigotatsu")
    public String horigotatsu;
    // 座敷
    @Column(name = "tatami")
    public String tatami;
    // カード可
    @Column(name = "card")
    public String card;
    // 禁煙席
    @Column(name = "non_smoking")
    public String non_smoking;
    // 貸切可
    @Column(name = "charter")
    public String charter;
    // 携帯電話OK
    @Column(name = "ktai")
    public String ktai;
    // 駐車場
    @Column(name = "parking")
    public String parking;
    // バリアフリー
    @Column(name = "barrier_free")
    public String barrier_free;
    // その他設備
    @Column(name = "other_memo")
    public String other_memo;
    // ソムリエ
    @Column(name = "sommelier")
    public String sommelier;
    // オープンエア
    @Column(name = "open_air")
    public String open_air;
    // ライブ・ショー
    @Column(name = "show")
    public String show;
    // エンタメ設備
    @Column(name = "equipment")
    public String equipment;
    // カラオケ
    @Column(name = "karaoke")
    public String karaoke;
    // バンド演奏可
    @Column(name = "band")
    public String band;
    // TV・プロジェクター
    @Column(name = "tv")
    public String tv;
    // 英語メニュー
    @Column(name = "english")
    public String english;
    // ペット可
    @Column(name = "pet")
    public String pet;
    // お子様連れ
    @Column(name = "child")
    public String child;
    // ランチ
    @Column(name = "lunch")
    public String lunch;
    // 23時以降も営業
    @Column(name = "midnight")
    public String midnight;
    // 備考
    @Column(name = "shop_detail_memo")
    public String shop_detail_memo;

    public ShopData() {
    }

    public ShopData(Shop shop) {
        this.id = shop.id;
        this.name = shop.name;
        this.name_kana = shop.name_kana;
        this.logo_image = shop.logo_image;
        this.address = shop.address;
        this.lat = shop.lat;
        this.lng = shop.lng;

        this.pc_large_image = shop.photo.pc.l;
        this.pc_medium_image = shop.photo.pc.m;
        this.pc_small_image = shop.photo.pc.s;

        this.mobile_large_image = shop.photo.mobile.l;
        this.mobile_small_image = shop.photo.mobile.s;
        this.access = shop.access;
        this.mobile_access = shop.mobile_access;
        this.genre = shop.genre.name;

        this.open = shop.open;
        this.close = shop.close;
        this.party_capacity = shop.party_capacity;
        this.wifi = shop.wifi;
        this.wedding = shop.wedding;
        this.course = shop.course;
        this.free_drink = shop.free_drink;
        this.free_food = shop.free_food;
        this.private_room = shop.private_room;
        this.horigotatsu = shop.horigotatsu;
        this.tatami = shop.tatami;
        this.card = shop.card;
        this.non_smoking = shop.non_smoking;
        this.charter = shop.charter;
        this.ktai = shop.ktai;
        this.parking = shop.parking;
        this.barrier_free = shop.barrier_free;
        this.other_memo = shop.other_memo;
        this.sommelier = shop.sommelier;
        this.open_air = shop.open_air;
        this.show = shop.show;
        this.equipment = shop.equipment;
        this.karaoke = shop.karaoke;
        this.band = shop.band;
        this.tv = shop.tv;
        this.english = shop.english;
        this.pet = shop.pet;
        this.child = shop.child;
        this.lunch = shop.lunch;
        this.midnight = shop.midnight;
        this.shop_detail_memo = shop.shop_detail_memo;

    }

    private ShopData(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.name_kana = in.readString();
        this.logo_image = in.readString();
        this.address = in.readString();
        this.lat = in.readFloat();
        this.lng = in.readFloat();
        this.pc_large_image = in.readString();
        this.pc_medium_image = in.readString();
        this.pc_small_image = in.readString();
        this.mobile_large_image = in.readString();
        this.mobile_small_image = in.readString();
        this.access = in.readString();
        this.mobile_access = in.readString();
        this.genre = in.readString();
        this.open = in.readString();
        this.close = in.readString();
        this.party_capacity = in.readString();
        this.wifi = in.readString();
        this.wedding = in.readString();
        this.course = in.readString();
        this.free_drink = in.readString();
        this.free_food = in.readString();
        this.private_room = in.readString();
        this.horigotatsu = in.readString();
        this.tatami = in.readString();
        this.card = in.readString();
        this.non_smoking = in.readString();
        this.charter = in.readString();
        this.ktai = in.readString();
        this.parking = in.readString();
        this.barrier_free = in.readString();
        this.other_memo = in.readString();
        this.sommelier = in.readString();
        this.open_air = in.readString();
        this.show = in.readString();
        this.equipment = in.readString();
        this.karaoke = in.readString();
        this.band = in.readString();
        this.tv = in.readString();
        this.english = in.readString();
        this.pet = in.readString();
        this.child = in.readString();
        this.lunch = in.readString();
        this.midnight = in.readString();
        this.shop_detail_memo = in.readString();
    }

    @Override
    public ShopData clone() {

        // テーブル上のIDをコピーしないように手動コピー

        ShopData clone = new ShopData();
        clone.id = this.id;
        clone.name = this.name;
        clone.name_kana = this.name_kana;
        clone.logo_image = this.logo_image;
        clone.address = this.address;
        clone.lat = this.lat;
        clone.lng = this.lng;

        clone.pc_large_image = this.pc_large_image;
        clone.pc_medium_image = this.pc_medium_image;
        clone.pc_small_image = this.pc_small_image;

        clone.mobile_large_image = this.mobile_large_image;
        clone.mobile_small_image = this.mobile_small_image;
        clone.access = this.access;
        clone.mobile_access = this.mobile_access;
        clone.genre = this.genre;

        clone.open = this.open;
        clone.close = this.close;
        clone.party_capacity = this.party_capacity;
        clone.wifi = this.wifi;
        clone.wedding = this.wedding;
        clone.course = this.course;
        clone.free_drink = this.free_drink;
        clone.free_food = this.free_food;
        clone.private_room = this.private_room;
        clone.horigotatsu = this.horigotatsu;
        clone.tatami = this.tatami;
        clone.card = this.card;
        clone.non_smoking = this.non_smoking;
        clone.charter = this.charter;
        clone.ktai = this.ktai;
        clone.parking = this.parking;
        clone.barrier_free = this.barrier_free;
        clone.other_memo = this.other_memo;
        clone.sommelier = this.sommelier;
        clone.open_air = this.open_air;
        clone.show = this.show;
        clone.equipment = this.equipment;
        clone.karaoke = this.karaoke;
        clone.band = this.band;
        clone.tv = this.tv;
        clone.english = this.english;
        clone.pet = this.pet;
        clone.child = this.child;
        clone.lunch = this.lunch;
        clone.midnight = this.midnight;
        clone.shop_detail_memo = this.shop_detail_memo;

        return clone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.name_kana);
        dest.writeString(this.logo_image);
        dest.writeString(this.address);
        dest.writeFloat(this.lat);
        dest.writeFloat(this.lng);
        dest.writeString(this.pc_large_image);
        dest.writeString(this.pc_medium_image);
        dest.writeString(this.pc_small_image);
        dest.writeString(this.mobile_large_image);
        dest.writeString(this.mobile_small_image);
        dest.writeString(this.access);
        dest.writeString(this.mobile_access);
        dest.writeString(this.genre);
        dest.writeString(this.open);
        dest.writeString(this.close);
        dest.writeString(this.party_capacity);
        dest.writeString(this.wifi);
        dest.writeString(this.wedding);
        dest.writeString(this.course);
        dest.writeString(this.free_drink);
        dest.writeString(this.free_food);
        dest.writeString(this.private_room);
        dest.writeString(this.horigotatsu);
        dest.writeString(this.tatami);
        dest.writeString(this.card);
        dest.writeString(this.non_smoking);
        dest.writeString(this.charter);
        dest.writeString(this.ktai);
        dest.writeString(this.parking);
        dest.writeString(this.barrier_free);
        dest.writeString(this.other_memo);
        dest.writeString(this.sommelier);
        dest.writeString(this.open_air);
        dest.writeString(this.show);
        dest.writeString(this.equipment);
        dest.writeString(this.karaoke);
        dest.writeString(this.band);
        dest.writeString(this.tv);
        dest.writeString(this.english);
        dest.writeString(this.pet);
        dest.writeString(this.child);
        dest.writeString(this.lunch);
        dest.writeString(this.midnight);
        dest.writeString(this.shop_detail_memo);
    }
}
