package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class SendModel implements ISendModel {
    @Override
    public void Send(String uid, String content) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getpublishJoke(uid,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendBean value) {
                        if(value.getCode().equals("0"))
                        {
                            sendModelResponse.SendSuccess(value);
                        }
                        else if(value.getCode().equals("1"))
                        {
                            sendModelResponse.SendFailue(value.getMsg());
                        }
                        else
                        {
                            sendModelResponse.SendFailue(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        sendModelResponse.SendError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public SendModelResponse sendModelResponse;

    public void setSendModelResponse(SendModelResponse sendModelResponse) {
        this.sendModelResponse = sendModelResponse;
    }
    public interface SendModelResponse
    {
        void SendSuccess(SendBean value);
        void SendFailue(String msg);
        void SendError(Throwable e);
    }
}
