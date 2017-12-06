package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/12/5.
 */

public class SendVideoModel implements ISendVideoModel {
    @Override
    public void SendVideo(String uid, File videopath, File coverpath, String videoDesc, String latitude, String longitude) {
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("videoDesc",videoDesc)
                .addFormDataPart("latitude",latitude)//维度
                .addFormDataPart("longitude",longitude);//经度

        RequestBody requestVideopath=RequestBody.create(MediaType.parse("multipart/form-dat"),videopath);
        builder.addFormDataPart("videoFile",videopath.getName(),requestVideopath);
        RequestBody requestCoverpath=RequestBody.create(MediaType.parse("multipart/form-dat"),coverpath);
        builder.addFormDataPart("coverFile",coverpath.getName(),requestCoverpath);

        List<MultipartBody.Part> parts = builder.build().parts();
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().publishVideo(parts)
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
                            sendVideoResponse.SendVideoSuccess(value);
                        }
                        else if(value.getCode().equals("1"))
                        {
                            sendVideoResponse.SendVideoFailue(value.getCode());
                        }
                        else
                        {
                            sendVideoResponse.SendVideoFailue(value.getCode());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        sendVideoResponse.SendVideoError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public SendVideoResponse sendVideoResponse;

    public void setSendVideoResponse(SendVideoResponse sendVideoResponse) {
        this.sendVideoResponse = sendVideoResponse;
    }
    public interface SendVideoResponse
    {
        void SendVideoSuccess(SendBean value);
        void SendVideoFailue(String msg);
        void SendVideoError(Throwable e);
    }
}
