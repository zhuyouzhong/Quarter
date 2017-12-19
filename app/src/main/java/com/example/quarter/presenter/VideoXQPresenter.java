package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.VideoXQBean;
import com.example.quarter.model.VideoXQModel;
import com.example.quarter.view.VideoXQView;

/**
 * Created by 祝文 on 2017/12/18.
 */

public class VideoXQPresenter extends BasePresenter<VideoXQView> implements VideoXQModel.VideoXQSuccess {
    private VideoXQModel videoXQModel;
    private  VideoXQView videoXQView;

    public VideoXQPresenter(VideoXQView mView) {
        super(mView);
        this.videoXQView=mView;
        videoXQModel=new VideoXQModel();
        videoXQModel.setVideoXQSuccess(this);
    }

    public void VideoXQSuccessPresenter(String wid){
        videoXQModel.VideoXQ(wid);
    }
    @Override
    public void VideoXQSuccess(VideoXQBean videoXQBean) {
        videoXQView.VideoXQSuccess(videoXQBean);
    }

    @Override
    public void VideoXQFailue(String msg) {
        videoXQView.VideoXQFailue(msg);
    }

    @Override
    public void VideoXQError(Throwable e) {
        videoXQView.VideoXQError(e);
    }
}
