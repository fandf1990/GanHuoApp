package io.gank.feng24k.app.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.gank.feng24k.app.Image.ImageAnalysisUtil;

public class RecommendInfo implements Parcelable {

    private String title;
    private String content;
    private String publishedAt;
    private String photoUrl;
    private List<RecommendItemInfo> mRecommendItemInfos;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

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

    public RecommendInfo htmlAnalysis(){
        RecommendInfo info = ImageAnalysisUtil.htmlAnalysis(this.content);
        if(info!=null) {
            this.mRecommendItemInfos = info.getRecommendItemInfos();
            this.photoUrl = info.getPhotoUrl();
            this.content = null;
        }
        return this;
    }

    public RecommendInfo() {
    }

    public List<RecommendItemInfo> getRecommendItemInfos() {
        return mRecommendItemInfos;
    }

    public void setRecommendItemInfos(List<RecommendItemInfo> recommendItemInfos) {
        mRecommendItemInfos = recommendItemInfos;
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
        dest.writeString(this.photoUrl);
        dest.writeTypedList(mRecommendItemInfos);
    }

    protected RecommendInfo(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.publishedAt = in.readString();
        this.photoUrl = in.readString();
        this.mRecommendItemInfos = in.createTypedArrayList(RecommendItemInfo.CREATOR);
    }

    public static final Creator<RecommendInfo> CREATOR = new Creator<RecommendInfo>() {
        public RecommendInfo createFromParcel(Parcel source) {
            return new RecommendInfo(source);
        }

        public RecommendInfo[] newArray(int size) {
            return new RecommendInfo[size];
        }
    };
}
