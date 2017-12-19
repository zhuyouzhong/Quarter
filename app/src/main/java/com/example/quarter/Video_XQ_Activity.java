package com.example.quarter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.VideoXQBean;
import com.example.quarter.presenter.VideoXQPresenter;
import com.example.quarter.view.VideoXQView;
import com.meg7.widget.CustomShapeImageView;

import static com.example.quarter.myapp.MyApp.context;

public class Video_XQ_Activity extends BaseActivity<VideoXQPresenter> implements VideoXQView{

    private ImageView iv_left;
    private CustomShapeImageView iv;
    private TextView tv_ms;
    private RelativeLayout rl;

    @Override
    public VideoXQPresenter initPresenter() {
        VideoXQPresenter videoXQPresenter=new VideoXQPresenter(this);
        return videoXQPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video__xq_;
    }

    @Override
    public void initView() {

        init();
        initOnClick();
    }

    private void initOnClick() {
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void init() {
        iv_left = findViewById(R.id.iv_left);
        iv = findViewById(R.id.iv);
        tv_ms = findViewById(R.id.tv_ms);
        rl = findViewById(R.id.rl);

        Intent intent = getIntent();
        String wid = intent.getStringExtra("wid");
        presenter.VideoXQSuccessPresenter(wid);
    }


    @Override
    public void VideoXQSuccess(final VideoXQBean videoXQBean) {

        System.out.println("获取视频详情+"+videoXQBean.getMsg());
        final String icon = videoXQBean.getData().getUser().getIcon();
        Glide.with(Video_XQ_Activity.this).load(icon).into(iv);
        tv_ms.setText(videoXQBean.getData().getWorkDesc());

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Video_XQ_Activity.this, UserVideoActivity.class);
                intent.putExtra("icon",icon);
                intent.putExtra("uid",videoXQBean.getData().getUid()+"");
                intent.putExtra("name",videoXQBean.getData().getUser().getNickname());

                startActivity(intent);
            }
        });

        View inflate = LayoutInflater.from(Video_XQ_Activity.this).inflate(R.layout.simple_player_view_player, rl);
        String videoUrl = videoXQBean.getData().getVideoUrl();
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        System.out.println("视频路径+++++"+replace);
        PlayerView playerVie=new PlayerView(Video_XQ_Activity.this,inflate)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(context)
                                .load(videoXQBean.getData().getCover())
                                .into(ivThumbnail);
                    }
                })
                .hideBack(true)
                .hideCenterPlayer(false)
                .setPlaySource(replace);

    }

    @Override
    public void VideoXQFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(Video_XQ_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void VideoXQError(Throwable e) {

    }
}
