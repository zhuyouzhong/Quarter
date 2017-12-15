package com.example.quarter.model;

import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.VersionBean;
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

public class UpdateModel {
    public void Update()
    {
        new NetRequestUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladaperFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {

                        if(versionBean.getCode().equals("0"))
                        {
                            updateSuccess.UpdateSuccess(versionBean);
                        }
                        else if(versionBean.getCode().equals("1"))
                        {
                            updateSuccess.UpdateFailue(versionBean.getCode());
                        }
                        else
                        {
                            updateSuccess.UpdateFailue(versionBean.getCode());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        updateSuccess.UpdateError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public UpdateSuccess updateSuccess;

    public void setUpdateSuccess(UpdateSuccess updateSuccess) {
        this.updateSuccess = updateSuccess;
    }
    public interface UpdateSuccess
    {
        void UpdateSuccess(VersionBean versionBean);
        void UpdateFailue(String msg);
        void UpdateError(Throwable e);
    }
}
