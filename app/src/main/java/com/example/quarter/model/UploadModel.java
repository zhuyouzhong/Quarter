package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.utils.NetRequestUtils;

import java.io.File;
import java.util.ArrayList;
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
 * Created by 祝文 on 2017/12/1.
 */

public class UploadModel implements IUploadModel {
    @Override
    public void Upload(String uid, ArrayList<String> list) {
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("uid",uid);
        if(list!=null)
        {
            for (int i = 0; i < list.size(); i++) {
                File file=new File(list.get(i));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file",file.getName(),requestBody);
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().upload(parts)
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
                            uploadModelResponse.UploadSuccess(value);
                        }
                        else if(value.getCode().equals("1"))
                        {
                             uploadModelResponse.UploadFailue(value.getCode());
                        }
                        else
                        {
                           uploadModelResponse.UploadFailue(value.getCode());

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

    @Override
    public void UpdateNickName(String uid, String nickname) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().updateNickName(uid,nickname)
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
                            uploadModelResponse.UploadSuccess(value);
                        }
                        else if(value.getCode().equals("1"))
                        {
                            uploadModelResponse.UploadFailue(value.getCode());
                        }
                        else
                        {
                            uploadModelResponse.UploadFailue(value.getCode());

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


    public UploadModelResponse uploadModelResponse;

    public void setUploadModelResponse(UploadModelResponse uploadModelResponse) {
        this.uploadModelResponse = uploadModelResponse;
    }

    public interface UploadModelResponse
    {
        void UploadSuccess(SendBean value);
        void UploadFailue(String msg);
        void UploadError(Throwable e);
    }
}
