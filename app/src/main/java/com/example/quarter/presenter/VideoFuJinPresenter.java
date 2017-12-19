package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.VideoFuJinBean;
import com.example.quarter.model.VideoFuJinModel;
import com.example.quarter.view.VideoFuJinView;

/**
 * Created by 祝文 on 2017/12/18.
 */

public class VideoFuJinPresenter extends BasePresenter<VideoFuJinView> implements VideoFuJinModel.VideoFuJinModelResson {

    private VideoFuJinModel videoFuJinModel;
    private  VideoFuJinView videoFuJinView;

    public VideoFuJinPresenter(VideoFuJinView mView) {
        super(mView);
        this.videoFuJinView=mView;
        videoFuJinModel=new VideoFuJinModel();
        videoFuJinModel.setVideoHotModelResson(this);
    }

    public void VideoFuJinPresenterSuccess(String page, String latitude, String longitude)
    {
        videoFuJinModel.VideoFujin(page,latitude,longitude);
    }
    @Override
    public void VideoFuJinSuccess(VideoFuJinBean videoFuJinBean) {
        videoFuJinView.VideoFuJinSuccess(videoFuJinBean);
    }

    @Override
    public void VideoFuJinFailue(String msg) {
        videoFuJinView.VideoFuJinFailue(msg);
    }

    @Override
    public void VideoFuJinError(Throwable e) {
        videoFuJinView.VideoFuJinError(e);
    }
}
