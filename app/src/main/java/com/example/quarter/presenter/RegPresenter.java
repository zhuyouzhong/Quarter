package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.model.RegModel;
import com.example.quarter.view.RegView;

import okhttp3.ResponseBody;

/**
 * Created by 祝文 on 2017/11/14.
 */

public class RegPresenter extends BasePresenter<RegView> implements RegModel.RegModelResponse{

    private RegView mView;
    private RegModel regModel;
    public RegPresenter(RegView mView) {
        super(mView);
        this.mView=mView;
        regModel=new RegModel();
        regModel.setRegModelResponse(this);
    }

    public void PresenterSuccess(String mobile, String password)
    {
        regModel.Reg(mobile,password);
    }

    @Override
    public void RegSuccess(ResponseBody responseBody) {
        mView.RegSuccess(responseBody);
    }
}
