package com.example.quarter.model;

import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VideoFuJinBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/7.
 */

public class VideoFuJinModel implements IVideoFuJinModel {


    @Override
    public void VideoFujin(String page, String latitude, String longitude) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getNearVideos(page,latitude,longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoFuJinBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoFuJinBean videoFuJinBean) {
                        if(videoFuJinBean.getCode().equals("0"))
                        {
                            videoFuJinModelResson.VideoFuJinSuccess(videoFuJinBean);
                        }
                        else if(videoFuJinBean.getCode().equals("1"))
                        {
                            videoFuJinModelResson.VideoFuJinFailue(videoFuJinBean.getCode());
                        }
                        else
                        {
                            videoFuJinModelResson.VideoFuJinFailue(videoFuJinBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        videoFuJinModelResson.VideoFuJinError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public VideoFuJinModelResson videoFuJinModelResson;

    public void setVideoHotModelResson(VideoFuJinModelResson videoFuJinModelResson) {
        this.videoFuJinModelResson = videoFuJinModelResson;
    }

    public interface VideoFuJinModelResson
    {
        void VideoFuJinSuccess(VideoFuJinBean videoFuJinBean);
        void VideoFuJinFailue(String msg);
        void VideoFuJinError(Throwable e);
    }
}
