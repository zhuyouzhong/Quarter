package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class SendModel implements ISendModel {
    @Override
    public void Send(String uid, String content, ArrayList<String> path) {
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("uid",uid)
        .addFormDataPart("content",content);
        if(path!=null)
        {
            for (int i = 0; i < path.size(); i++) {
                File file=new File(path.get(i));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("jokeFiles",file.getName(),requestBody);
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();

        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getpublishJoke(parts)
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
                            sendModelResponse.SendFailue(value.getCode());
                        }
                        else
                        {
                            sendModelResponse.SendFailue(value.getCode());

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
        /*if(videopath!=null)
        {
            for (int i = 0; i < videopath.size(); i++) {
                LocalMedia localMedia = videopath.get(i);
                String path = localMedia.getPath();
                System.out.println("=====视频路径====="+path);
                File file=new File(path);
                RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                builder.addFormDataPart("videoFile",file.getName(),requestBody);
            }
        }
        if(coverpath!=null)
        {
            for (int i = 0; i < coverpath.size(); i++) {

                File file=new File(coverpath.get(i).toString());
                System.out.println("=====封面路径====="+coverpath.get(i).toString());
                RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                builder.addFormDataPart("coverFile",file.getName(),requestBody);
            }
        }

        List<MultipartBody.Part> parts = builder.build().parts();*/

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
                            sendModelResponse.SendSuccess(value);
                        }
                        else if(value.getCode().equals("1"))
                        {
                            sendModelResponse.SendFailue(value.getCode());
                        }
                        else
                        {
                            sendModelResponse.SendFailue(value.getCode());

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
