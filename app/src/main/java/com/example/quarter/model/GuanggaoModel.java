package com.example.quarter.model;

import com.example.quarter.bean.Guanggao;
import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/1.
 */

public class GuanggaoModel {
    public void guanggao()
    {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Guanggao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Guanggao value) {
                        if(value.getCode().equals("0"))
                        {
                            guanggaoModelSuccess.GuanggaoSuccess(value);
                        }
                        else  if(value.getCode().equals("1"))
                        {
                            guanggaoModelSuccess.GuanggaoFailue(value.getMsg());
                        }
                        else
                        {
                            guanggaoModelSuccess.GuanggaoFailue(value.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public GuanggaoModelSuccess guanggaoModelSuccess;

    public void setGuanggaoModelSuccess(GuanggaoModelSuccess guanggaoModelSuccess) {
        this.guanggaoModelSuccess = guanggaoModelSuccess;
    }
    public interface GuanggaoModelSuccess
    {
        void GuanggaoSuccess(Guanggao value);
        void GuanggaoFailue(String msg);
        void GuanggaoError(Throwable e);
    }
}
