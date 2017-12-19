package com.example.quarter.model;

import com.example.quarter.bean.HQUserBean;
import com.example.quarter.bean.MyGuanzhuBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/16.
 */

public class MyZuoPinModel implements IMyZuoPinModel {
    @Override
    public void MyZuoPin(String uid) {
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
                    public void onNext(HQUserBean hqUserBean) {
                        if(hqUserBean.getCode().equals("0"))
                        {
                            myZuoPinSuccess.MyZuoPinSuccess(hqUserBean);
                        }
                        else if(hqUserBean.getCode().equals("1"))
                        {
                            myZuoPinSuccess.MyZuoPinFailue(hqUserBean.getCode());
                        }
                        else
                        {
                            myZuoPinSuccess.MyZuoPinFailue(hqUserBean.getCode());

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        myZuoPinSuccess.MyZuoPinError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MyZuoPinSuccess myZuoPinSuccess;

    public void setMyZuoPinSuccess(MyZuoPinSuccess myZuoPinSuccess) {
        this.myZuoPinSuccess = myZuoPinSuccess;
    }
    public interface MyZuoPinSuccess
    {
        void MyZuoPinSuccess(HQUserBean hqUserBean);
        void MyZuoPinFailue(String msg);
        void MyZuoPinError(Throwable e);
    }
}
