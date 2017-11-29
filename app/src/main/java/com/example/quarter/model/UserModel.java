package com.example.quarter.model;

import com.example.quarter.bean.UserBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/27.
 */

public class UserModel implements IUserModel {
    @Override
    public void GetUserInfo(String uid) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        userModelResponse.UserSuccess(userBean);
                    }
                });
    }

    public UserModelResponse userModelResponse;

    public void setUserModelResponse(UserModelResponse userModelResponse) {
        this.userModelResponse = userModelResponse;
    }
    public interface UserModelResponse
    {
        void UserSuccess(UserBean userBean);
    }
}
