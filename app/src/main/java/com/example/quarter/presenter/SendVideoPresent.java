package com.example.quarter.presenter;

import android.content.SharedPreferences;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.SendBean;
import com.example.quarter.model.SendModel;
import com.example.quarter.model.SendVideoModel;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.view.SendVideoView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/12/5.
 */

public class SendVideoPresent extends BasePresenter<SendVideoView> implements SendVideoModel.SendVideoResponse {

    private SendVideoView sendVideoView;
    private SendVideoModel sendVideoModel;
    public SendVideoPresent(SendVideoView mView) {
        super(mView);
        this.sendVideoView=mView;
        sendVideoModel=new SendVideoModel();
        sendVideoModel.setSendVideoResponse(this);
    }

    public void SendVideoPresenterSuccess(String uid, File videopath, File coverpath, String videoDesc, String latitude, String longitude)
    {
        sendVideoModel.SendVideo(uid,videopath,coverpath,videoDesc,latitude,longitude);
    }
    @Override
    public void SendVideoSuccess(SendBean value) {
        sendVideoView.SendVideoSuccess(value);
    }

    @Override
    public void SendVideoFailue(String msg) {
        sendVideoView.SendVideoFailue(msg);
    }

    @Override
    public void SendVideoError(Throwable e) {
        sendVideoView.SendVideoError(e);
    }
}
