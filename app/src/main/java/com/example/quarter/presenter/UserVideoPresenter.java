package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
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
    public void UserPresenterSuccess(String uid)
    {

        userVideoModel.User(uid);
    }
    public void GuanzhuPresenterSuccess(String uid,String followId)
    {
        userVideoModel.Guanzhu(uid,followId);
    }
    public void HQUserPresenterSuccess(String uid)
    {
        userVideoModel.HQUser(uid);
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



    @Override
    public void UserSuccess(UserBean userBean) {
        userVideoView.UserSuccess(userBean);
    }

    @Override
    public void UserFailue(String msg) {
        userVideoView.UserFailue(msg);
    }

    @Override
    public void UserError(Throwable e) {
        userVideoView.UserError(e);
    }



    @Override
    public void GuanzhuSuccess(SendBean sendBean) {
        userVideoView.GuanzhuSuccess(sendBean);
    }

    @Override
    public void GuanzhuFailue(String msg) {
        userVideoView.GuanzhuFailue(msg);
    }

    @Override
    public void GuanzhuError(Throwable e) {
        userVideoView.GuanzhuError(e);
    }

    @Override
    public void HQUserSuccess(HQUserBean sendBean) {
        userVideoView.HQUserSuccess(sendBean);
    }

    @Override
    public void HQUserFailue(String msg) {
        userVideoView.HQUserFailue(msg);
    }

    @Override
    public void HQUserError(Throwable e) {
        userVideoView.HQUserError(e);
    }
}
