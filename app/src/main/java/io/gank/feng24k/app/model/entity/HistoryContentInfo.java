package io.gank.feng24k.app.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class HistoryContentInfo implements Parcelable {

    private String title;
    private String content;
    private String publishedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.publishedAt);
    }

    public HistoryContentInfo() {
    }

    protected HistoryContentInfo(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.publishedAt = in.readString();
    }

    public static final Parcelable.Creator<HistoryContentInfo> CREATOR = new Parcelable.Creator<HistoryContentInfo>() {
        public HistoryContentInfo createFromParcel(Parcel source) {
            return new HistoryContentInfo(source);
        }

        public HistoryContentInfo[] newArray(int size) {
            return new HistoryContentInfo[size];
        }
    };
}
