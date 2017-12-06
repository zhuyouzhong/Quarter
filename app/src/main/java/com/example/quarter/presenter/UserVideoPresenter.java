package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.model.UserVideoModel;
import com.example.quarter.view.UserVideoView;

/**
 * Created by 祝文 on 2017/12/6.
 */

public class UserVideoPresenter extends BasePresenter<UserVideoView> implements UserVideoModel.UserVideoReson {

    private UserVideoView userVideoView;
    private UserVideoModel userVideoModel;
    public UserVideoPresenter(UserVideoView mView) {
        super(mView);
        this.userVideoView=mView;
        userVideoModel=new UserVideoModel();
        userVideoModel.setUserVideoReson(this);
    }

    public void UserVideoPresenterSuccess(String uid,String page)
    {
        userVideoModel.UserVideo(uid,page);
    }
    @Override
    public void UserVideoSuccess(UserVideoBean userVideoBean) {
        userVideoView.UserVideoSuccess(userVideoBean);
    }

    @Override
    public void UserVideoFailue(String msg) {
        userVideoView.UserVideoFailue(msg);
    }

    @Override
    public void UserVideoError(Throwable e) {
        userVideoView.UserVideoError(e);
    }
}
