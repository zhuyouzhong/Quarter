package com.example.quarter.view;

import com.example.quarter.bean.VideoXQBean;

/**
 * Created by 祝文 on 2017/12/18.
 */

public interface VideoXQView {
    void VideoXQSuccess(VideoXQBean videoXQBean);
    void VideoXQFailue(String msg);
    void VideoXQError(Throwable e);
}
