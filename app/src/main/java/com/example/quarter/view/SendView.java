package com.example.quarter.view;

import com.example.quarter.bean.SendBean;

import okhttp3.ResponseBody;

/**
 * Created by 祝文 on 2017/11/28.
 */

public interface SendView {
    void SendSuccess(SendBean value);
    void SendFailue(String msg);
    void SendError(Throwable e);
}
