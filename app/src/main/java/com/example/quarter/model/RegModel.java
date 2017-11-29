package com.example.quarter.model;

import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/14.
 * 使用链式调用
 */

public class RegModel implements IRegModel {
    @Override
    public void Reg(String mobile, String password) {

            new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                    .build().getApiService().getUser(mobile,password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            regModelResponse.RegSuccess(responseBody);
                        }
                    });
    }
    public RegModelResponse regModelResponse;
    public void setRegModelResponse(RegModelResponse regModelResponse) {
        this.regModelResponse = regModelResponse;
    }
    public interface RegModelResponse
    {
        void RegSuccess(ResponseBody responseBody);
    }
}
