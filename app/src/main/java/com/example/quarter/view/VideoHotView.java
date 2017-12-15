package com.example.quarter.view;

import com.example.quarter.bean.UserVideoBean;

/**
 * Created by 祝文 on 2017/12/7.
 */

public interface VideoHotView {
    void VideoHotSuccess(UserVideoBean userVideoBean);
    void VideoHotFailue(String msg);
    void VideoHotError(Throwable e);
}
