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

        for(int i=1;i<categoryName.size();i++){
            RecommendItemInfo recommendItemInfo = new RecommendItemInfo();
            Element h3 = categoryName.get(i);
            recommendItemInfo.setCytagoryName(h3.text());

            List<RecommendItemInfo.BaseItemInfo> baseItemInfoList = new ArrayList<>();
            Elements hrefElement = h3.nextElementSibling().select("a[href]");
            for(Element herf:hrefElement){
                if(!herf.text().equals("")){
                    RecommendItemInfo.BaseItemInfo info = new RecommendItemInfo.BaseItemInfo();
                    info.setText(herf.text());
                    info.setUrl(herf.attr("href"));
                    baseItemInfoList.add(info);
                }
//                else{
//                    String photo = herf.select("img[src]").get(0).attr("src");
//                    if(!TextUtils.isEmpty(photo)) {
//                        RecommendItemInfo.BaseItemInfo info = new RecommendItemInfo.BaseItemInfo();
//                        info.setPhotoUrl(photo);
//                        baseItemInfoList.add(info);
//                    }
//                }
            }
            recommendItemInfo.setBaseItemInfos(baseItemInfoList);
            recommendItemInfos.add(recommendItemInfo);
        }
        recommendInfo.setRecommendItemInfos(recommendItemInfos);
        return recommendInfo;
    }


    public static RecommendInfo htmlAnalysisOld(String content){
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
            String name = e.text();
            if(!TextUtils.isEmpty(name)) {
                info.setCytagoryName(name);
                recommendItemInfos.add(info);
            }
        }
        Elements categoryData = document.getElementsByTag("ul");
        int count = recommendItemInfos.size();
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
