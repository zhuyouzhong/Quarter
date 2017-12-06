package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.adapter.MyUserVideoXRecycleView;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.UserVideoBean;
import com.example.quarter.presenter.UserVideoPresenter;
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

    @Override
    public UserVideoPresenter initPresenter() {
        UserVideoPresenter userVideoPresenter=new UserVideoPresenter(this);
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
        iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num%2==0)
                {
                    iv_guanzhu.setVisibility(View.GONE);
                    iv_yiguanzhu.setVisibility(View.VISIBLE);
                    num++;
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
        });

        iv_geren_zuojiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inView() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        icon = intent.getStringExtra("icon");
        name = intent.getStringExtra("name");
        System.out.println("---->"+ uid +"----->"+ icon +"----->"+ name);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(name);
        csi_iv = findViewById(R.id.csi_iv);
        Glide.with(this).load(icon).into(csi_iv);
        iv_guanzhu = findViewById(R.id.iv_guanzhu);
        iv_yiguanzhu = findViewById(R.id.iv_yiguanzhu);
        iv_geren_zuojiantou = findViewById(R.id.iv_geren_zuojiantou);

        xrl_user_video = findViewById(R.id.xrl_user_video);

        list = new ArrayList<>();
        presenter.UserVideoPresenterSuccess(uid,page+"");
    }

    @Override
    public void UserVideoSuccess(UserVideoBean userVideoBean) {

        list.addAll(userVideoBean.getData());
        MyUserVideoXRecycleView myUserVideoXRecycleView=new MyUserVideoXRecycleView(list,UserVideoActivity.this,icon,name);
        xrl_user_video.setLayoutManager(new LinearLayoutManager(UserVideoActivity.this));
        xrl_user_video.setAdapter(myUserVideoXRecycleView);
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

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_video);

        initView();
        initOnClick();
    }

    private void initOnClick() {
        iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num%2==0)
                {
                    iv_guanzhu.setVisibility(View.GONE);
                    iv_yiguanzhu.setVisibility(View.VISIBLE);
                    num++;
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
        });

        iv_geren_zuojiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        String icon = intent.getStringExtra("icon");
        String name = intent.getStringExtra("name");
        System.out.println("---->"+uid+"----->"+icon+"----->"+name);
        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(name);
        csi_iv = findViewById(R.id.csi_iv);
        Glide.with(this).load(icon).into(csi_iv);
        iv_guanzhu = findViewById(R.id.iv_guanzhu);
        iv_yiguanzhu = findViewById(R.id.iv_yiguanzhu);
        iv_geren_zuojiantou = findViewById(R.id.iv_geren_zuojiantou);


        xrl_user_video = findViewById(R.id.xrl_user_video);
    }*/
}
