package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserVideoBean;
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

public class VideoHotModel implements IVideoHotModel {
    @Override
    public void VideoHot(String page) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getHotVideos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserVideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserVideoBean userVideoBean) {
                        if(userVideoBean.getCode().equals("0"))
                        {
                            videoHotModelResson.VideoHotSuccess(userVideoBean);
                        }
                        else if(userVideoBean.getCode().equals("1"))
                        {
                            videoHotModelResson.VideoHotFailue(userVideoBean.getCode());
                        }
                        else
                        {
                            videoHotModelResson.VideoHotFailue(userVideoBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        videoHotModelResson.VideoHotError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public VideoHotModelResson videoHotModelResson;

    public void setVideoHotModelResson(VideoHotModelResson videoHotModelResson) {
        this.videoHotModelResson = videoHotModelResson;
    }
    public interface VideoHotModelResson
    {
        void VideoHotSuccess(UserVideoBean userVideoBean);
        void VideoHotFailue(String msg);
        void VideoHotError(Throwable e);
    }
}
