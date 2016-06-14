package io.gank.feng24k.app.Image;


import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.model.entity.RecommendInfo;
import io.gank.feng24k.app.model.entity.RecommendItemInfo;

public class ImageAnalysisUtil {

    public static RecommendInfo htmlAnalysis(String content){
        if(TextUtils.isEmpty(content)){
            return null;
        }
        RecommendInfo recommendInfo = new RecommendInfo();
        Document document = Jsoup.parse(content);
        recommendInfo.setPhotoUrl(document.getElementsByTag("img").get(0).attr("src"));
        Elements categoryName = document.getElementsByTag("h3");
        List<RecommendItemInfo> recommendItemInfos = new ArrayList<>();
        for(Element e:categoryName){
            RecommendItemInfo info = new RecommendItemInfo();
            info.setCytagoryName(e.text());
            recommendItemInfos.add(info);
        }
        Elements categoryData = document.getElementsByTag("ul");
        int count = categoryData.size();
        for(int i = 0;i<count;i++){
            RecommendItemInfo recommendItemInfo = recommendItemInfos.get(i);
            List<RecommendItemInfo.BaseItemInfo> baseItemInfos = new ArrayList<>();
            Elements links = categoryData.get(i).select("a[href]");
            for(Element two:links){
                RecommendItemInfo.BaseItemInfo info = new RecommendItemInfo.BaseItemInfo();
                info.setText(two.text());
                info.setUrl(two.attr("href"));
                baseItemInfos.add(info);
            }
            recommendItemInfo.setBaseItemInfos(baseItemInfos);
        }
        recommendInfo.setRecommendItemInfos(recommendItemInfos);
        return recommendInfo;
    }

}
