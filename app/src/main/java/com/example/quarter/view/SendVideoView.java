package com.example.quarter.view;

import com.example.quarter.bean.SendBean;

/**
 * Created by 祝文 on 2017/12/5.
 */

public interface SendVideoView {
    void SendVideoSuccess(SendBean value);
    void SendVideoFailue(String msg);
    void SendVideoError(Throwable e);
}
