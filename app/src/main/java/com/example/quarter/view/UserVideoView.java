package com.example.quarter.view;

import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
import com.example.quarter.bean.UserVideoBean;

/**
 * Created by 祝文 on 2017/12/6.
 */

public interface UserVideoView {
    void UserVideoSuccess(UserVideoBean userVideoBean);
    void UserVideoFailue(String msg);
    void UserVideoError(Throwable e);

    void UserSuccess(UserBean userBean);
    void UserFailue(String msg);
    void UserError(Throwable e);

    void GuanzhuSuccess(SendBean sendBean);
    void GuanzhuFailue(String msg);
    void GuanzhuError(Throwable e);

    void HQUserSuccess(HQUserBean sendBean);
    void HQUserFailue(String msg);
    void HQUserError(Throwable e);
}
