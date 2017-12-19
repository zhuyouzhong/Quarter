package com.example.quarter.view;

import com.example.quarter.bean.HQUserBean;

/**
 * Created by 祝文 on 2017/12/16.
 */

public interface MyZuoPinView {
    void MyZuoPinSuccess(HQUserBean hqUserBean);
    void MyZuoPinFailue(String msg);
    void MyZuoPinError(Throwable e);
}
