package com.example.quarter.view;

import com.example.quarter.bean.Guanggao;

/**
 * Created by 祝文 on 2017/12/1.
 */

public interface GuanggaoView {
    void GuanggaoSuccess(Guanggao value);
    void GuanggaoFailue(String msg);
    void GuanggaoError(Throwable e);
}
