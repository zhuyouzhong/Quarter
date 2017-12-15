package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.VersionBean;
import com.example.quarter.model.UpdateModel;
import com.example.quarter.view.UpdateView;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class UpdatePresenter extends BasePresenter<UpdateView> implements UpdateModel.UpdateSuccess {

    private UpdateModel updateModel;
    private UpdateView updateView;
    public UpdatePresenter(UpdateView mView) {
        super(mView);
        this.updateView=mView;
        updateModel=new UpdateModel();
        updateModel.setUpdateSuccess(this);
    }


    public void UpdateSuccessPresenter()
    {
        updateModel.Update();
    }
    @Override
    public void UpdateSuccess(VersionBean versionBean) {
        updateView.UpdateSuccess(versionBean);
    }

    @Override
    public void UpdateFailue(String msg) {
        updateView.UpdateFailue(msg);
    }

    @Override
    public void UpdateError(Throwable e) {
        updateView.UpdateError(e);
    }
}
