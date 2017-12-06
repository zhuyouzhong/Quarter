package com.example.quarter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.SendBean;
import com.example.quarter.presenter.SendVideoPresent;
import com.example.quarter.public_class.GlideLoader;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.SendVideoView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShangchuanVideoActivity extends BaseActivity<SendVideoPresent> implements SendVideoView {

    private Button bt_fb;
    private String videopath;
    private Button bt_shezhi;
    private ArrayList<String> path = new ArrayList<>();
    private ImageView iv_tihuan;
    private EditText et_conter;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String longitude;
    private String latitude;
    private TextView tv_fanhui;

    @Override
    public SendVideoPresent initPresenter() {
        SendVideoPresent sendVideoPresent=new SendVideoPresent(this);
        return sendVideoPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shangchuan_video;
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        videopath = intent.getStringExtra("videopath");

        inView();
        initOnCLick();
    }

    private void initOnCLick() {
        bt_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(videopath);
                System.out.println(path.get(0).toString());
                String s = et_conter.getText().toString();

                File file=new File(videopath);
                File file1=new File(path.get(0).toString());
                presenter.SendVideoPresenterSuccess(MyInterceptor.id,file,file1,s,latitude,longitude);


                ProgressDialog mypDialog=new ProgressDialog(ShangchuanVideoActivity.this);
                //实例化
                mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                //设置进度条风格，风格为圆形，旋转的
                mypDialog.setTitle("提示");
                //设置ProgressDialog 标题
                mypDialog.setMessage("正在上传...");
                //设置ProgressDialog 提示信息
                mypDialog.setIcon(R.drawable.ic_launcher_background);
                //设置ProgressDialog 标题图标
                mypDialog.setIndeterminate(false);
                //设置ProgressDialog 的进度条是否不明确
                mypDialog.setCancelable(true);
                //设置ProgressDialog 是否可以按退回按键取消
                mypDialog.show();
                //让ProgressDialog显示
            }
        });
        bt_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageConfig imageConfig = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        // .mutiSelect()
                        //剪切
                        //.crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(1)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(ShangchuanVideoActivity.this, imageConfig);   // 开启图片选择器

            }
        });
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inView() {
        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
        bt_fb = findViewById(R.id.bt_fb);
        bt_shezhi = findViewById(R.id.bt_shezhi);
        iv_tihuan = findViewById(R.id.iv_tihuan);
        et_conter = findViewById(R.id.et_conter);
        tv_fanhui = findViewById(R.id.tv_fanhui);
    }

    @Override
    public void SendVideoSuccess(SendBean value) {
        Toast.makeText(this, value.getMsg(), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ShangchuanVideoActivity.this,SendSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void SendVideoFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(ShangchuanVideoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void SendVideoError(Throwable e) {

    }

   public static final int REQUEST_CODE = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }
            path.clear();
            path.addAll(pathList);
            //mySendRecycleView.notifyDataSetChanged();

            Glide.with(ShangchuanVideoActivity.this).load(path.get(0).toString()).into(iv_tihuan);

        }

    }


    //经纬度的获取
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(final AMapLocation aMapLocation) {
            runOnUiThread(new Runnable() {


                @Override
                public void run() {
                    // System.out.println("纬度"+aMapLocation.getLatitude() + "+++++经度" + aMapLocation.getLongitude());
                    latitude = aMapLocation.getLatitude()+"";

                    longitude = aMapLocation.getLongitude()+"";
                    System.out.println("纬度"+latitude + "+++++经度" +longitude);
                    locationClient.stopLocation();
                }
            });

        }
    };
}
