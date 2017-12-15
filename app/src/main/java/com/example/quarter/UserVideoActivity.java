package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.adapter.MyUserVideoXRecycleView;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.presenter.UserVideoPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.UserVideoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;

import java.util.ArrayList;
import java.util.List;

public class UserVideoActivity extends BaseActivity<UserVideoPresenter> implements UserVideoView {

    private TextView tv_name;
    private CustomShapeImageView csi_iv;
    private TextView iv_guanzhu;
    private Button iv_yiguanzhu;
    private int num=0;
    private ImageView iv_geren_zuojiantou;
    private XRecyclerView xrl_user_video;
    private String uid;
    private int page=1;
    private List<UserVideoBean.DataBean> list;
    private String icon;
    private String name;
    private UserVideoPresenter userVideoPresenter;
    private MyUserVideoXRecycleView myUserVideoXRecycleView;



    @Override
    public UserVideoPresenter initPresenter() {
        userVideoPresenter = new UserVideoPresenter(this);
        return userVideoPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_video;
    }

    @Override
    public void initView() {

        inView();
        initOnClick();
    }

    private void initOnClick() {
       /* iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num%2==0)
                {
                    iv_guanzhu.setVisibility(View.GONE);
                    iv_yiguanzhu.setVisibility(View.VISIBLE);
                    num++;
                    presenter.GuanzhuPresenterSuccess(MyInterceptor.id,uid);
                }
            }
        });
        iv_yiguanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(num%2!=0)
                {
                    iv_guanzhu.setVisibility(View.VISIBLE);
                    iv_yiguanzhu.setVisibility(View.GONE);
                    num++;
                }

            }
        });*/
        iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.GuanzhuPresenterSuccess(MyInterceptor.id,uid);
            }
        });
        iv_yiguanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.GuanzhuPresenterSuccess(MyInterceptor.id,uid);

            }
        });

        iv_geren_zuojiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inView() {
        View view = LayoutInflater.from(UserVideoActivity.this).inflate(R.layout.user_video_item, null);
        iv_guanzhu = view.findViewById(R.id.iv_guanzhu);
        iv_yiguanzhu =view. findViewById(R.id.iv_yiguanzhu);
        iv_geren_zuojiantou = view.findViewById(R.id.iv_geren_zuojiantou);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        icon = intent.getStringExtra("icon");
        name = intent.getStringExtra("name");
   System.out.println("---->"+ uid +"----->"+ icon +"----->"+ name);
        tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(name);
        csi_iv =view. findViewById(R.id.csi_iv);
        Glide.with(this).load(icon).into(csi_iv);

        xrl_user_video = findViewById(R.id.xrl_user_video);
        xrl_user_video.addHeaderView(view);
        list = new ArrayList<>();
        presenter.UserPresenterSuccess(uid);
    }

    @Override
    public void UserVideoSuccess(UserVideoBean userVideoBean) {
        System.out.println("+++++++++++++++++"+userVideoBean.getMsg());
        list.addAll(userVideoBean.getData());

        if(myUserVideoXRecycleView==null)
        {
            myUserVideoXRecycleView = new MyUserVideoXRecycleView(list,UserVideoActivity.this,icon,name);
            xrl_user_video.setLayoutManager(new LinearLayoutManager(UserVideoActivity.this));
            xrl_user_video.setAdapter(myUserVideoXRecycleView);
        }
        else
        {
            myUserVideoXRecycleView.notifyDataSetChanged();
        }
        xrl_user_video.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.UserVideoPresenterSuccess(uid,page+"");
                xrl_user_video.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                presenter.UserVideoPresenterSuccess(uid,page+"");
                xrl_user_video.loadMoreComplete();
            }
        });

    }

    @Override
    public void UserVideoFailue(String msg) {

        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void UserVideoError(Throwable e) {

    }
    @Override
    public void UserSuccess(UserBean userBean) {
        presenter.UserVideoPresenterSuccess(uid,page+"");

    }

    @Override
    public void UserFailue(String msg) {

    }
    @Override
    public void UserError(Throwable e) {

    }


    //关注
    @Override
    public void GuanzhuSuccess(SendBean sendBean) {
        Toast.makeText(this, sendBean.getMsg(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void GuanzhuFailue(String msg) {

        if(msg.equals("1"))
        {
            Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show();

        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(UserVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void GuanzhuError(Throwable e) {

    }

}
