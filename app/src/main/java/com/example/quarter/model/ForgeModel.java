package com.example.quarter.model;

import com.example.quarter.bean.ForgeBean;
import com.example.quarter.utils.NetRequestUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝文 on 2017/11/28.
 */

public class ForgeModel implements IForgeModel {
    @Override
    public void Forge(String page) {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getJokes(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForgeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ForgeBean value) {
                        if("0".equals(value.getCode()))
                        {
                            forgeModelPesponse.ForgeSuccess(value);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            throw (e);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    public ForgeModelPesponse forgeModelPesponse;

    public void setForgeModelPesponse(ForgeModelPesponse forgeModelPesponse) {
        this.forgeModelPesponse = forgeModelPesponse;
    }
    public interface ForgeModelPesponse
    {
        void ForgeSuccess(ForgeBean forgeBean);
    }
}
