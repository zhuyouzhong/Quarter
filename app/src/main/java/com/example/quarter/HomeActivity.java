package com.example.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.UserBean;
import com.example.quarter.fragment.Forge_Fragment;
import com.example.quarter.fragment.Groom_Fragment;
import com.example.quarter.fragment.Video_Fragment;
import com.example.quarter.presenter.UserPresenter;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.UserView;
import com.meg7.widget.CustomShapeImageView;

public class HomeActivity extends BaseActivity<UserPresenter> implements UserView {

    private CustomShapeImageView csiv;
    private DrawerLayout draw;
    private LinearLayout ll_groom;
    private LinearLayout ll_forge;
    private LinearLayout ll_video;
    private Groom_Fragment groom_fragment;
    private Forge_Fragment forge_fragment;
    private Video_Fragment video_fragment;
    private ImageView iv_groom;
    private ImageView iv_forge;
    private ImageView iv_video;
    private TextView tv_groom;
    private TextView tv_forge;
    private TextView tv_video;
    private RelativeLayout rl_left_run;
    private CustomShapeImageView draw_csiv;
    private TextView tv_nickname;
    private TextView tv_tuijian;
    private ImageView iv_send;

    @Override
    public UserPresenter initPresenter() {
        UserPresenter userPresenter=new UserPresenter(this);
        return userPresenter;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }
    @Override
    public void initView() {
        draw = findViewById(R.id.draw);
        csiv = findViewById(R.id.csiv);
        draw_csiv = findViewById(R.id.draw_csiv);
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_tuijian = findViewById(R.id.tv_tuijian);
        ll_groom = findViewById(R.id.ll_groom);
        ll_forge = findViewById(R.id.ll_forge);
        ll_video = findViewById(R.id.ll_video);
        groom_fragment = new Groom_Fragment();
        forge_fragment = new Forge_Fragment();
        video_fragment = new Video_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.rl_fm, groom_fragment).show(groom_fragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rl_fm, forge_fragment).hide(forge_fragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rl_fm, video_fragment).hide(video_fragment).commit();
        iv_groom = findViewById(R.id.iv_groom);
        iv_forge = findViewById(R.id.iv_forge);
        iv_video = findViewById(R.id.iv_video);
        tv_groom = findViewById(R.id.tv_groom);
        tv_forge = findViewById(R.id.tv_forge);
        tv_video = findViewById(R.id.tv_video);
        iv_groom.setImageResource(R.drawable.tj_lan);
        tv_groom.setTextColor(getResources().getColor(R.color.textcolor));
        rl_left_run = findViewById(R.id.rl_left_run);
        iv_send = findViewById(R.id.iv_send);
        initOnClick();

        if(!TextUtils.isEmpty(MyInterceptor.tk)||!TextUtils.isEmpty(MyInterceptor.id))
        {
            presenter.UserPersenterSuccess(MyInterceptor.id);
        }
    }
    @Override
    public void UserSuccess(UserBean userBean) {
        System.out.println("------"+userBean.getMsg());
        String icon = userBean.getData().getIcon();
        String nickname = userBean.getData().getNickname();
        Glide.with(HomeActivity.this).load(icon).into(csiv);
        Glide.with(HomeActivity.this).load(icon).into(draw_csiv);
        tv_nickname.setText(nickname);
    }



    private void initOnClick() {
        draw.setScrimColor(Color.TRANSPARENT);
        csiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.openDrawer(Gravity.LEFT);
            }
        });

        ll_groom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().show(groom_fragment).commit();
                getSupportFragmentManager().beginTransaction().hide(forge_fragment).commit();
                getSupportFragmentManager().beginTransaction().hide(video_fragment).commit();

                iv_groom.setImageResource(R.drawable.tj_lan);
                iv_forge.setImageResource(R.drawable.dz);
                iv_video.setImageResource(R.drawable.sp);
                tv_groom.setTextColor(getResources().getColor(R.color.textcolor));
                tv_forge.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                tv_video.setTextColor(getResources().getColor(R.color.textcolor_yuan));

                tv_tuijian.setText("推荐");

            }
        });
        ll_forge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().hide(groom_fragment).commit();
                getSupportFragmentManager().beginTransaction().show(forge_fragment).commit();
                getSupportFragmentManager().beginTransaction().hide(video_fragment).commit();

                iv_groom.setImageResource(R.drawable.tj);
                iv_forge.setImageResource(R.drawable.dz_lan);
                iv_video.setImageResource(R.drawable.sp);
                tv_groom.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                tv_forge.setTextColor(getResources().getColor(R.color.textcolor));
                tv_video.setTextColor(getResources().getColor(R.color.textcolor_yuan));

                tv_tuijian.setText("段子");
            }
        });
        ll_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().hide(groom_fragment).commit();
                getSupportFragmentManager().beginTransaction().hide(forge_fragment).commit();
                getSupportFragmentManager().beginTransaction().show(video_fragment).commit();

                iv_groom.setImageResource(R.drawable.tj);
                iv_forge.setImageResource(R.drawable.dz);
                iv_video.setImageResource(R.drawable.sp_lan);
                tv_groom.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                tv_forge.setTextColor(getResources().getColor(R.color.textcolor_yuan));
                tv_video.setTextColor(getResources().getColor(R.color.textcolor));
                tv_tuijian.setText("视频");

            }
        });
        rl_left_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(MyInterceptor.id))
                {
                    Toast.makeText(HomeActivity.this, "個人中心", Toast.LENGTH_SHORT).show();
                 }
                if(TextUtils.isEmpty(MyInterceptor.id))
                {
                    Toast.makeText(HomeActivity.this, "Token過期", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }



}
