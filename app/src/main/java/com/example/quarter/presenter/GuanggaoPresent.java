package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.GroomHotBean;
import com.example.quarter.bean.Guanggao;
import com.example.quarter.model.GuanggaoModel;
import com.example.quarter.view.GuanggaoView;

/**
 * Created by 祝文 on 2017/12/1.
 */

public class GuanggaoPresent extends BasePresenter<GuanggaoView> implements GuanggaoModel.GuanggaoModelSuccess{

    private GuanggaoView guanggaoView;
    private GuanggaoModel guanggaoModel;

    public GuanggaoPresent(GuanggaoView mView) {
        super(mView);
        this.guanggaoView=mView;
        guanggaoModel=new GuanggaoModel();
        guanggaoModel.setGuanggaoModelSuccess(this);
    }
    public void GuanggaoPresentSuccess(String uid,String type,String page)
    {
        guanggaoModel.guanggao();
        guanggaoModel.GroomHot(uid,type,page);
    }
    @Override
    public void GuanggaoSuccess(Guanggao value) {
        guanggaoView.GuanggaoSuccess(value);
    }

    @Override
    public void GuanggaoFailue(String msg) {
        guanggaoView.GuanggaoFailue(msg);
    }

    @Override
    public void GuanggaoError(Throwable e) {
        guanggaoView.GuanggaoError(e);
    }

    @Override
    public void GroomHotSuccess(GroomHotBean value) {
        guanggaoView.GroomHotSuccess(value);
    }

    @Override
    public void GroomHotFailue(String msg) {
        guanggaoView.GroomHotFailue(msg);
    }

    @Override
    public void GroomHotError(Throwable e) {
        guanggaoView.GroomHotError(e);
    }


}
