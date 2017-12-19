package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/19.
 */

public class VideoPLModel implements IVideoPLModel {

    @Override
    public void VideoPL(String uid, String wid, String content) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().comment(uid,wid,content)
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
                            videoPLSuccess.VideoPLSuccess(sendBean);
                        }
                        else if(sendBean.getCode().equals("1"))
                        {
                            videoPLSuccess.VideoPLFailue(sendBean.getCode());
                        }
                        else
                        {
                            videoPLSuccess.VideoPLFailue(sendBean.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        videoPLSuccess.VideoPLError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
     public VideoPLSuccess videoPLSuccess;

    public void setVideoPLSuccess(VideoPLSuccess videoPLSuccess) {
        this.videoPLSuccess = videoPLSuccess;
    }

    public interface VideoPLSuccess
    {
        void VideoPLSuccess(SendBean sendBean);
        void VideoPLFailue(String msg);
        void VideoPLError(Throwable e);
    }
}
