package io.feng24k.app.gank.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RecommendItemInfo implements Parcelable {
    private String cytagoryName;
    private List<BaseItemInfo> mBaseItemInfos;

    public String getCytagoryName() {
        return cytagoryName;
    }

    public void setCytagoryName(String cytagoryName) {
        this.cytagoryName = cytagoryName;
    }

    public List<BaseItemInfo> getBaseItemInfos() {
        return mBaseItemInfos;
    }

    public void setBaseItemInfos(List<BaseItemInfo> baseItemInfos) {
        mBaseItemInfos = baseItemInfos;
    }


    public static class BaseItemInfo implements Parcelable {
        private String text;
        private String url;
        private String photoUrl;

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.text);
            dest.writeString(this.url);
        }

        public BaseItemInfo() {
        }

        protected BaseItemInfo(Parcel in) {
            this.text = in.readString();
            this.url = in.readString();
        }

        public static final Parcelable.Creator<BaseItemInfo> CREATOR = new Parcelable.Creator<BaseItemInfo>() {
            public BaseItemInfo createFromParcel(Parcel source) {
                return new BaseItemInfo(source);
            }

            public BaseItemInfo[] newArray(int size) {
                return new BaseItemInfo[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cytagoryName);
        dest.writeTypedList(mBaseItemInfos);
    }

    public RecommendItemInfo() {
    }

    protected RecommendItemInfo(Parcel in) {
        this.cytagoryName = in.readString();
        this.mBaseItemInfos = in.createTypedArrayList(BaseItemInfo.CREATOR);
    }

    public static final Parcelable.Creator<RecommendItemInfo> CREATOR = new Parcelable.Creator<RecommendItemInfo>() {
        public RecommendItemInfo createFromParcel(Parcel source) {
            return new RecommendItemInfo(source);
        }

        public RecommendItemInfo[] newArray(int size) {
            return new RecommendItemInfo[size];
        }
    };
}
