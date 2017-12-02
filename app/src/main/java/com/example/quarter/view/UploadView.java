package com.example.quarter.view;

import com.example.quarter.bean.SendBean;

/**
 * Created by 祝文 on 2017/12/1.
 */

public interface UploadView
{
    void UploadSuccess(SendBean value);
    void UploadFailue(String msg);
    void UploadError(Throwable e);
}
