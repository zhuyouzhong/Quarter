package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.LoginBean;
import com.example.quarter.model.LoginModel;
import com.example.quarter.view.LoginView;

import java.util.List;

/**
 * Created by 祝文 on 2017/11/27.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.LoginModelResponse {

    private LoginView loginView;
    private LoginModel loginModel;
    public LoginPresenter(LoginView mView) {
        super(mView);
        this.loginView=mView;
        loginModel=new LoginModel();
        loginModel.setRegModelResponse(this);
    }

    public void LoginPresenterSuccess(String mobile,String password)
    {
        loginModel.Login(mobile,password);
    }


    @Override
    public void LoginSuccess(LoginBean loginBean) {
        loginView.LoginSuccess(loginBean);
    }
}
