package com.example.quarter.view;

import com.example.quarter.bean.SendBean;

/**
 * Created by 祝文 on 2017/12/19.
 */

public interface VideoPLView {
    void VideoPLSuccess(SendBean sendBean);
    void VideoPLFailue(String msg);
    void VideoPLError(Throwable e);
}
