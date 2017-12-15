package com.example.quarter.view;

import com.example.quarter.bean.VersionBean;

/**
 * Created by 祝文 on 2017/12/15.
 */

public interface UpdateView {
    void UpdateSuccess(VersionBean versionBean);
    void UpdateFailue(String msg);
    void UpdateError(Throwable e);
}
