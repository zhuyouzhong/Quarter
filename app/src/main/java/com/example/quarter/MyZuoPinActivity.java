package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.adapter.MyZuoPinRecycler;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.HQUserBean;
import com.example.quarter.presenter.MyZuoPinPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.MyZuoPinView;

import java.util.ArrayList;
import java.util.List;

public class MyZuoPinActivity extends BaseActivity<MyZuoPinPresenter> implements MyZuoPinView {


    private RelativeLayout rl_bendi;
    private RelativeLayout rl_sc;
    private TextView tv_bendi;
    private TextView tv_ysc;
    private View xhx1;
    private View xhx2;
    private TextView tv_fanhui;
    private List<HQUserBean.DataBean.WorksEntitiesBean> list;
    private RecyclerView rlv_myzuopin;
    private SharedPreferences fl;

    @Override
    public MyZuoPinPresenter initPresenter() {
        MyZuoPinPresenter myZuoPinPresenter=new MyZuoPinPresenter(this);
        return myZuoPinPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_zuo_pin;
    }

    @Override
    public void initView() {
        init();
        initOnClick();
    }

    private void initOnClick() {
        rl_bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_bendi.setTextColor(getResources().getColor(R.color.textcolor));
                xhx1.setVisibility(View.VISIBLE);
                tv_ysc.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                xhx2.setVisibility(View.GONE);
                list.clear();
                presenter.MyZuoPinPresenterSuccess(MyInterceptor.id);
                SharedPreferences.Editor edit = fl.edit();
                edit.putBoolean("fenlei",false);
                edit.commit();
            }
        });
        rl_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_bendi.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                xhx1.setVisibility(View.GONE);
                tv_ysc.setTextColor(getResources().getColor(R.color.textcolor));
                xhx2.setVisibility(View.VISIBLE);

                list.clear();
                presenter.MyZuoPinPresenterSuccess(MyInterceptor.id);
                SharedPreferences.Editor edit = fl.edit();
                edit.putBoolean("fenlei",true);
                edit.commit();
            }
        });
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        rl_bendi = findViewById(R.id.rl_bendi);
        rl_sc = findViewById(R.id.rl_sc);
        tv_bendi = findViewById(R.id.tv_bendi);
        tv_ysc = findViewById(R.id.tv_ysc);
        xhx1 = findViewById(R.id.xhx1);
        xhx2 = findViewById(R.id.xhx2);
        tv_fanhui = findViewById(R.id.tv_fanhui);
        rlv_myzuopin = findViewById(R.id.rlv_myzuopin);

        tv_bendi.setTextColor(getResources().getColor(R.color.textcolor));
        xhx1.setVisibility(View.VISIBLE);
        list = new ArrayList<>();
        fl = getSharedPreferences("FL", MODE_PRIVATE);
       fl.edit().clear().commit();
       fl.edit().putBoolean("fenlei",false).commit();
        boolean fenlei = fl.getBoolean("fenlei", true);
        if(fenlei==false)
        {
            presenter.MyZuoPinPresenterSuccess(MyInterceptor.id);
        }

    }

    @Override
    public void MyZuoPinSuccess(HQUserBean hqUserBean) {
        list.addAll(hqUserBean.getData().getWorksEntities());
        MyZuoPinRecycler myZuoPinRecycler=new MyZuoPinRecycler(list,MyZuoPinActivity.this);
        rlv_myzuopin.setLayoutManager(new GridLayoutManager(MyZuoPinActivity.this,3));
        rlv_myzuopin.setAdapter(myZuoPinRecycler);
        System.out.println("用户+"+hqUserBean.getMsg());
    }

    @Override
    public void MyZuoPinFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(MyZuoPinActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void MyZuoPinError(Throwable e) {

    }

}
