package com.example.quarter.model;

import com.example.quarter.bean.MyGuanzhuBean;
import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class MyGuanzhu implements IMyGuanzhuModel {
    @Override
    public void MyGuanzhu(String uid) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getFollowUsers(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyGuanzhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyGuanzhuBean myGuanzhuBean) {
                        if(myGuanzhuBean.getCode().equals("0"))
                        {
                            myGuanzhuSuccess.MyGuanzhuSuccess(myGuanzhuBean);
                        }
                        else if(myGuanzhuBean.getCode().equals("1"))
                        {
                            myGuanzhuSuccess.MyGuanzhuFailue(myGuanzhuBean.getCode());
                        }
                        else
                        {
                            myGuanzhuSuccess.MyGuanzhuFailue(myGuanzhuBean.getCode());

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        myGuanzhuSuccess.MyGuanzhuError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MyGuanzhuSuccess myGuanzhuSuccess;

    public void setMyGuanzhuSuccess(MyGuanzhuSuccess myGuanzhuSuccess) {
        this.myGuanzhuSuccess = myGuanzhuSuccess;
    }
    public interface MyGuanzhuSuccess
    {
        void MyGuanzhuSuccess(MyGuanzhuBean myGuanzhuBean);
        void MyGuanzhuFailue(String msg);
        void MyGuanzhuError(Throwable e);
    }
}
