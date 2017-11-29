package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.UserBean;
import com.example.quarter.model.UserModel;
import com.example.quarter.view.UserView;

/**
 * Created by 祝文 on 2017/11/27.
 */

public class UserPresenter extends BasePresenter<UserView> implements UserModel.UserModelResponse {
    private UserView userView;
    private UserModel userModel;
    public UserPresenter(UserView mView) {
        super(mView);
        this.userView=mView;
        userModel=new UserModel();
        userModel.setUserModelResponse(this);
    }

    public void UserPersenterSuccess(String uid)
    {
        userModel.GetUserInfo(uid);
    }
    @Override
    public void UserSuccess(UserBean userBean) {
        userView.UserSuccess(userBean);
    }
}
