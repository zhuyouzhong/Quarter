package com.example.quarter.view;

import com.example.quarter.bean.VideoFuJinBean;

/**
 * Created by 祝文 on 2017/12/18.
 */

public interface VideoFuJinView {
    void VideoFuJinSuccess(VideoFuJinBean videoFuJinBean);
    void VideoFuJinFailue(String msg);
    void VideoFuJinError(Throwable e);
}
