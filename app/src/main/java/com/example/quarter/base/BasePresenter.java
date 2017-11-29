package com.example.quarter.base;

/**
 * Created by 祝文 on 2017/11/14.
 */

public class BasePresenter<V> {

    public V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void deatach()
    {
        this.mView=null;
    }
}
