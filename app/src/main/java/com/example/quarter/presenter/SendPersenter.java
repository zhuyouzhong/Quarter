package com.example.quarter.presenter;

import android.content.SharedPreferences;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.SendBean;
import com.example.quarter.model.SendModel;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.view.SendView;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;

import okhttp3.ResponseBody;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class SendPersenter extends BasePresenter<SendView> implements SendModel.SendModelResponse {

    private SendView sendView;
    private SendModel sendModel;
    public SendPersenter(SendView mView) {
        super(mView);
        this.sendView=mView;
        sendModel=new SendModel();
        sendModel.setSendModelResponse(this);
    }

    public void SendPresenterSuccess(String uid, String content, ArrayList<String> path, File videopath, File coverpath, String videoDesc, String latitude, String longitude)
    {
        final SharedPreferences jz = MyApp.context.getSharedPreferences("JZ", MyApp.context.MODE_PRIVATE);
        boolean jz1 = jz.getBoolean("jz", true);
        if(jz1==true)
        {
            sendModel.Send(uid,content,path);
        }
      else
        {
            sendModel.SendVideo(uid,videopath,coverpath,videoDesc,latitude,longitude);

        }
    }

    @Override
    public void SendSuccess(SendBean value) {
        sendView.SendSuccess(value);
    }

    @Override
    public void SendFailue(String msg) {
        sendView.SendFailue(msg);
    }

    @Override
    public void SendError(Throwable e) {
        sendView.SendError(e);
    }
}
