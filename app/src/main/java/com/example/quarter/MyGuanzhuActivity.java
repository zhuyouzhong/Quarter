package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.adapter.MyGuanzhuRecycler;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.MyGuanzhuBean;
import com.example.quarter.presenter.MyGuanzhuPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.MyGuanzhuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyGuanzhuActivity extends BaseActivity<MyGuanzhuPresenter> implements MyGuanzhuView {

    private XRecyclerView xrlv_mygz;
    private List<MyGuanzhuBean.DataBean> list;
    private TextView tv_fanhui;

    @Override
    public MyGuanzhuPresenter initPresenter() {
        MyGuanzhuPresenter myGuanzhuPresenter=new MyGuanzhuPresenter(this);
        return myGuanzhuPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_guanzhu;
    }

    @Override
    public void initView() {

        init();
    }

    private void init() {
        xrlv_mygz = findViewById(R.id.xrlv_mygz);
        tv_fanhui = findViewById(R.id.tv_fanhui);
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<>();
        presenter.MyGuanzhuSuccessPresenter(MyInterceptor.id);
    }

    @Override
    public void MyGuanzhuSuccess(MyGuanzhuBean myGuanzhuBean) {
      list.addAll(myGuanzhuBean.getData());
        System.out.println("关注+"+myGuanzhuBean.getMsg());
        MyGuanzhuRecycler myGuanzhuRecycler=new MyGuanzhuRecycler(list,MyGuanzhuActivity.this);
        xrlv_mygz.setLayoutManager(new LinearLayoutManager(MyGuanzhuActivity.this));
        xrlv_mygz.setAdapter(myGuanzhuRecycler);
    }

    @Override
    public void MyGuanzhuFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(MyGuanzhuActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void MyGuanzhuError(Throwable e) {

    }
}
