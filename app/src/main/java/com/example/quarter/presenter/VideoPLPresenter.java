package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.SendBean;
import com.example.quarter.model.VideoPLModel;
import com.example.quarter.view.VideoPLView;

/**
 * Created by 祝文 on 2017/12/19.
 */

public class VideoPLPresenter extends BasePresenter<VideoPLView> implements VideoPLModel.VideoPLSuccess{
    private VideoPLModel videoPLModel;
    private VideoPLView videoPLView;
    public VideoPLPresenter(VideoPLView mView) {
        super(mView);
        this.videoPLView=mView;
        videoPLModel=new VideoPLModel();
        videoPLModel.setVideoPLSuccess(this);
    }

    public void VidepPLPresenterSuccess(String uid,String wid,String content)
    {
        videoPLModel.VideoPL(uid,wid,content);
    }
    @Override
    public void VideoPLSuccess(SendBean sendBean) {
        videoPLView.VideoPLSuccess(sendBean);
    }

    @Override
    public void VideoPLFailue(String msg) {
        videoPLView.VideoPLFailue(msg);
    }

    @Override
    public void VideoPLError(Throwable e) {
        videoPLView.VideoPLError(e);
    }
}
