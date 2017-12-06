package com.example.quarter.model;

import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/6.
 */

public class UserVideoModel implements IUserVideoModel {
    @Override
    public void UserVideo(String uid, String page) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getUserVideos(uid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserVideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserVideoBean userVideoBean) {

                        if(userVideoBean.getCode().equals("0"))
                        {
                            userVideoReson.UserVideoSuccess(userVideoBean);
                        }
                        else if(userVideoBean.getCode().equals("1"))
                        {
                            userVideoReson.UserVideoFailue(userVideoBean.getCode());
                        }
                        else
                        {
                            userVideoReson.UserVideoFailue(userVideoBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        userVideoReson.UserVideoError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public UserVideoReson userVideoReson;

    public void setUserVideoReson(UserVideoReson userVideoReson) {
        this.userVideoReson = userVideoReson;
    }
    public interface UserVideoReson
    {
        void UserVideoSuccess(UserVideoBean userVideoBean);
        void UserVideoFailue(String msg);
        void UserVideoError(Throwable e);
    }
}
