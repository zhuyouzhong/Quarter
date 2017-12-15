package com.example.quarter.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by 祝文 on 2017/11/14.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    public P presenter;
    public abstract P initPresenter();
    public abstract int getLayoutId();
    public abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    getSupportActionBar().hide();

        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        presenter=initPresenter();
        initView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deatach();
    }
}
