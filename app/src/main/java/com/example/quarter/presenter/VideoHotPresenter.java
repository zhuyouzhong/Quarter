package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.model.VideoHotModel;
import com.example.quarter.view.VideoHotView;

/**
 * Created by 祝文 on 2017/12/7.
 */

public class VideoHotPresenter extends BasePresenter<VideoHotView> implements VideoHotModel.VideoHotModelResson {
    private VideoHotView videoHotView;
    private VideoHotModel videoHotModel;

    public VideoHotPresenter(VideoHotView mView) {
        super(mView);
        this.videoHotView=mView;
        videoHotModel=new VideoHotModel();
        videoHotModel.setVideoHotModelResson(this);
    }

    public void VideoHotPresenterSuccess(String page)
    {
        videoHotModel.VideoHot(page);
    }

    @Override
    public void VideoHotSuccess(UserVideoBean userVideoBean) {
        videoHotView.VideoHotSuccess(userVideoBean);
    }

    @Override
    public void VideoHotFailue(String msg) {
        videoHotView.VideoHotFailue(msg);
    }

    @Override
    public void VideoHotError(Throwable e) {
        videoHotView.VideoHotError(e);
    }
}
