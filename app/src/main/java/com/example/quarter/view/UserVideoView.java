package com.example.quarter.view;

import com.example.quarter.bean.UserVideoBean;

/**
 * Created by 祝文 on 2017/12/6.
 */

public interface UserVideoView {
    void UserVideoSuccess(UserVideoBean userVideoBean);
    void UserVideoFailue(String msg);
    void UserVideoError(Throwable e);
}
