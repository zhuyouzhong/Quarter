package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.model.MyZuoPinModel;
import com.example.quarter.view.MyZuoPinView;

/**
 * Created by 祝文 on 2017/12/16.
 */

public class MyZuoPinPresenter extends BasePresenter<MyZuoPinView> implements MyZuoPinModel.MyZuoPinSuccess {
    private MyZuoPinModel myZuoPinModel;
    private MyZuoPinView myZuoPinView;

    public MyZuoPinPresenter(MyZuoPinView mView) {
        super(mView);
        this.myZuoPinView=mView;
        myZuoPinModel =new MyZuoPinModel();
        myZuoPinModel.setMyZuoPinSuccess(this);
    }
    public void MyZuoPinPresenterSuccess(String uid)
    {
        myZuoPinModel.MyZuoPin(uid);
    }

    @Override
    public void MyZuoPinSuccess(HQUserBean hqUserBean) {
        myZuoPinView.MyZuoPinSuccess(hqUserBean);
    }

    @Override
    public void MyZuoPinFailue(String msg) {
        myZuoPinView.MyZuoPinFailue(msg);
    }

    @Override
    public void MyZuoPinError(Throwable e) {
        myZuoPinView.MyZuoPinError(e);
    }
}
