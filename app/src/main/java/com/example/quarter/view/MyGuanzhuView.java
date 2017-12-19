package com.example.quarter.view;

import com.example.quarter.bean.MyGuanzhuBean;

/**
 * Created by 祝文 on 2017/12/15.
 */

public interface MyGuanzhuView {
    void MyGuanzhuSuccess(MyGuanzhuBean myGuanzhuBean);
    void MyGuanzhuFailue(String msg);
    void MyGuanzhuError(Throwable e);
}
