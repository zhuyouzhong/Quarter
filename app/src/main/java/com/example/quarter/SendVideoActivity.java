package com.example.quarter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

public class SendVideoActivity extends AppCompatActivity {

    private ImageView iv_dong;
    private ImageView iv_anniu;
    private RelativeLayout play;
    private ArrayList<LocalMedia> selectList = new ArrayList<>();
    private TextView tv_fanhui;
    private Button bt_xyb;
    private PlayerView playerVie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_send_video);


        initView();
        initOnCLick();
    }

    private void initOnCLick() {
        iv_anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(SendVideoActivity.this)
                        .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        // .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(0)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .previewVideo(true)// 是否可预览视频 true or false
                        .enablePreviewAudio(true) // 是否可播放音频 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        //.glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        //.withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(true)// 是否显示gif图片 true or false
                        //.compressSavePath(getPath())//压缩图片保存地址
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .openClickSound(true)// 是否开启点击声音 true or false
                        .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                        // .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                        //.cropCompressQuality()// 裁剪压缩质量 默认90 int
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //  .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                        .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                        .videoQuality(0)// 视频录制质量 0 or 1 int
                        .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                        .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectList.size()!=0)
                {
                    playerVie.stopPlay();
                }
                finish();
            }
        });
        bt_xyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectList.size()==0)
                {
                    Toast.makeText(SendVideoActivity.this, "请录制视频", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    System.out.println("视频地址................."+selectList.get(0).getPath());
                    Intent intent=new Intent(SendVideoActivity.this,ShangchuanVideoActivity.class);
                    intent.putExtra("videopath",selectList.get(0).getPath());
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {

        iv_dong = findViewById(R.id.iv_dong);
        Glide.with(this).load("http://stc.zjol.com.cn/g1/M001E85CggSBFfD0NuARE-0AEAe8Xiq8Zk179.gif?width=700&height=467").into(iv_dong);
        iv_anniu = findViewById(R.id.iv_anniu);
        play = findViewById(R.id.play);

        tv_fanhui = findViewById(R.id.tv_fanhui);
        bt_xyb = findViewById(R.id.bt_xyb);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selec = PictureSelector.obtainMultipleResult(data);
                    System.out.println("00000000000000000000"+selec.get(0).getPath());
                   /* for (int i = 0; i <selec.size() ; i++) {
                        LocalMedia localMedia = selec.get(i);
                        path1 = localMedia.getPath();
                    }*/

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                   /* adapter.setList(selectList);
                    adapter.notifyDataSetChanged();*/
                    selectList.clear();
                    selectList.addAll(selec);
                    View inflate = LayoutInflater.from(SendVideoActivity.this).inflate(R.layout.simple_player_view_player,play);
                    playerVie = new PlayerView(SendVideoActivity.this,inflate)
                            .setScaleType(PlayStateParams.fitparent)
                            .hideMenu(true)
                            .forbidTouch(false)
                            .hideBack(true)
                            .operatorPanl()
                            .hideMenu(true)
                            .hideRotation(true)
                            .hideFullscreen(true)
                            .hideCenterPlayer(true)
                            .setShowSpeed(false)
                            .hideBottonBar(true)
                            .hideBottonBar(true)
                            .hideControlPanl(true)
                            .setPlaySource(selectList.get(0).getPath());
                    playerVie.startPlay();

                    if(selectList.size()==0)
                    {
                        iv_dong.setVisibility(View.VISIBLE);
                        play.setVisibility(View.GONE);
                    }
                    else
                    {
                        iv_dong.setVisibility(View.GONE);
                        play.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }
}
