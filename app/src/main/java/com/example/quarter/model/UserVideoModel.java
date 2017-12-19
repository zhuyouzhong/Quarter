package com.example.quarter.model;

import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
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

    @Override
    public void User(String uid) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        if(userBean.getCode().equals("0"))
                        {
                            userVideoReson.UserSuccess(userBean);
                        }
                        else if(userBean.getCode().equals("1"))
                        {
                            userVideoReson.UserFailue(userBean.getCode());
                        }
                        else
                        {
                            userVideoReson.UserFailue(userBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        userVideoReson.UserError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void Guanzhu(String uid,String followId) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getfollow(uid,followId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<SendBean>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(SendBean sendBean) {
                         if(sendBean.getCode().equals("0"))
                         {
                             userVideoReson.GuanzhuSuccess(sendBean);
                         }
                         else if(sendBean.getCode().equals("1"))
                         {
                             userVideoReson.GuanzhuFailue(sendBean.getCode());
                         }
                         else
                         {
                             userVideoReson.GuanzhuFailue(sendBean.getCode());
                         }
                     }

                     @Override
                     public void onError(Throwable e) {
                         userVideoReson.GuanzhuError(e);
                     }

                     @Override
                     public void onComplete() {

                     }
                 });
    }

    @Override
    public void HQUser(String uid) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getWorkInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HQUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HQUserBean sendBean) {
                        if(sendBean.getCode().equals("0"))
                        {
                            userVideoReson.HQUserSuccess(sendBean);
                        }
                        else if(sendBean.getCode().equals("1"))
                        {
                            userVideoReson.HQUserFailue(sendBean.getCode());
                        }
                        else
                        {
                            userVideoReson.HQUserFailue(sendBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        userVideoReson.HQUserError(e);
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

        void UserSuccess(UserBean userBean);
        void UserFailue(String msg);
        void UserError(Throwable e);

        void GuanzhuSuccess(SendBean sendBean);
        void GuanzhuFailue(String msg);
        void GuanzhuError(Throwable e);

        void HQUserSuccess(HQUserBean sendBean);
        void HQUserFailue(String msg);
        void HQUserError(Throwable e);
    }
}
