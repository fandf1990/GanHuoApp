package io.gank.feng24k.app.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class BenefitEntity implements Parcelable {
    private String url;
    private String who;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.who);
    }

    public BenefitEntity() {
    }

    protected BenefitEntity(Parcel in) {
        this.url = in.readString();
        this.who = in.readString();
    }

    public static final Parcelable.Creator<BenefitEntity> CREATOR = new Parcelable.Creator<BenefitEntity>() {
        public BenefitEntity createFromParcel(Parcel source) {
            return new BenefitEntity(source);
        }

        public BenefitEntity[] newArray(int size) {
            return new BenefitEntity[size];
        }
    };
}
