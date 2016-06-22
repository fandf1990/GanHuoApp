package io.gank.feng24k.app.model.presentation.main;

import android.content.DialogInterface;
import android.os.Environment;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.robobinding.annotation.PresentationModel;

import java.io.File;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.PhotoViewActivity;
import okhttp3.Call;


@PresentationModel
public class PhotoViewPrestationModel extends BasePresentationModel implements  DialogInterface.OnClickListener{

    private BenefitEntity mBenefitEntity;
    private PhotoViewActivity mActivity;
    private String mDownloadUrl;
    private String mFileSavePath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"gank.io";

    public PhotoViewPrestationModel(PhotoViewActivity activity, BenefitEntity entity) {
        this.mBenefitEntity = entity;
        this.mActivity = activity;
    }

    public String getImageUrl() {
        return mBenefitEntity.getUrl();
    }


    public void startDownloadPhoto(){
        //mDownloadUrl="http://dldir1.qq.com/qqfile/qq/QQ8.4/18357/QQ8.4.exe";
        mDownloadUrl = mBenefitEntity.getUrl();
        mActivity.getProgressDialog().show();
        String fileName = mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/")+1,mDownloadUrl.length());
        OkHttpUtils
                .get()
                .url(mDownloadUrl)
                .tag(mDownloadUrl)
                .build()
                .execute(new FileCallBack(mFileSavePath, fileName){
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        int progressNum = (int) (progress*100);
                        mActivity.getProgressDialog().setProgress(progressNum);
                        if(progressNum==100){
                            mActivity.getProgressDialog().dismiss();
                            Toast.makeText(mActivity,"文件已保存到"+mFileSavePath,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mActivity.getProgressDialog().dismiss();
                        Toast.makeText(mActivity,"下载失败，请重试",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }
                });
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(mActivity,"已取消下载",Toast.LENGTH_SHORT).show();
        OkHttpUtils.getInstance().cancelTag(mDownloadUrl);
    }
}
