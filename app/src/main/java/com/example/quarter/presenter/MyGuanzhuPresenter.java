package com.example.quarter.presenter;

import com.example.quarter.base.BasePresenter;
import com.example.quarter.bean.MyGuanzhuBean;
import com.example.quarter.model.MyGuanzhu;
import com.example.quarter.view.MyGuanzhuView;

/**
 * Created by 祝文 on 2017/12/15.
 */

public class MyGuanzhuPresenter extends BasePresenter<MyGuanzhuView> implements MyGuanzhu.MyGuanzhuSuccess{
    private MyGuanzhuView myGuanzhuView;
    private MyGuanzhu myGuanzhu;

    public MyGuanzhuPresenter(MyGuanzhuView mView) {
        super(mView);
        this.myGuanzhuView=mView;
        myGuanzhu=new MyGuanzhu();
        myGuanzhu.setMyGuanzhuSuccess(this);
    }

    public void MyGuanzhuSuccessPresenter(String uid)
    {
        myGuanzhu.MyGuanzhu(uid);
    }
    @Override
    public void MyGuanzhuSuccess(MyGuanzhuBean myGuanzhuBean) {
        myGuanzhuView.MyGuanzhuSuccess(myGuanzhuBean);
    }

    @Override
    public void MyGuanzhuFailue(String msg) {
        myGuanzhuView.MyGuanzhuFailue(msg);
    }

    @Override
    public void MyGuanzhuError(Throwable e) {
        myGuanzhuView.MyGuanzhuError(e);
    }
}
