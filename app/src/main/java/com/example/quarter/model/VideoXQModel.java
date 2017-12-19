package com.example.quarter.model;

import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.bean.VideoXQBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/18.
 */

public class VideoXQModel implements IVideoXQModel {
    @Override
    public void VideoXQ(String wid) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getVideoDetail(wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoXQBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoXQBean videoXQBean) {
                        if(videoXQBean.getCode().equals("0"))
                        {
                            videoXQSuccess.VideoXQSuccess(videoXQBean);
                        }
                        else if(videoXQBean.getCode().equals("1"))
                        {
                            videoXQSuccess.VideoXQFailue(videoXQBean.getCode());
                        }
                        else
                        {
                            videoXQSuccess.VideoXQFailue(videoXQBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        videoXQSuccess.VideoXQError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public VideoXQSuccess videoXQSuccess;

    public void setVideoXQSuccess(VideoXQSuccess videoXQSuccess) {
        this.videoXQSuccess = videoXQSuccess;
    }

    public interface VideoXQSuccess
    {
        void VideoXQSuccess(VideoXQBean videoXQBean);
        void VideoXQFailue(String msg);
        void VideoXQError(Throwable e);
    }
}
