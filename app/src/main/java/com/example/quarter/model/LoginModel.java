package com.example.quarter.model;

import com.example.quarter.bean.LoginBean;
import com.example.quarter.utils.NetRequestUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/14.
 * 使用链式调用
 */

public class LoginModel implements ILoginModel {
    @Override
    public void Login(String mobile, String password) {

            new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                    .build().getApiService().getLogin(mobile,password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginBean>() {
                        @Override
                        public void accept(LoginBean loginBean) throws Exception {
                            loginModelResponse.LoginSuccess(loginBean);
                        }
                    });
    }
    public LoginModelResponse loginModelResponse;
    public void setRegModelResponse(LoginModelResponse loginModelResponse) {
        this.loginModelResponse = loginModelResponse;
    }
    public interface LoginModelResponse
    {
        void LoginSuccess(LoginBean loginBean);
    }
}
