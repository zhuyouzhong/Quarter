package com.example.quarter.view;

import com.example.quarter.bean.UserBean;

/**
 * Created by 祝文 on 2017/11/27.
 */

public interface UserView {
    void UserSuccess(UserBean value);
    void UserFaiul(String msg);
    void UserError(Throwable e);
}
