package com.example.quarter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.SendBean;
import com.example.quarter.bean.UserBean;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.presenter.UploadPresent;
import com.example.quarter.presenter.UserPresenter;
import com.example.quarter.public_class.GlideLoader;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.UploadView;
import com.example.quarter.view.UserView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.quarter.SendActivity.REQUEST_CODE;

public class CenterActivity extends BaseActivity<UploadPresent> implements UploadView {
    private ImageView iv_geren_touxiang;
    private TextView tv_geren_name;
    private TextView tv_geren_nicheng;
    private ImageView iv_geren_zuojiantou;
    private ArrayList<String> list = new ArrayList<>();
    private String s1;
    private RelativeLayout rl_nicheng;


    @Override
    public UploadPresent initPresenter() {
        UploadPresent uploadPresent=new UploadPresent(this);
        return uploadPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_center;
    }

    @Override
    public void initView() {
        iv_geren_touxiang = findViewById(R.id.iv_geren_touxiang);
        tv_geren_name = findViewById(R.id.tv_geren_name);
        tv_geren_nicheng = findViewById(R.id.tv_geren_nicheng);
        iv_geren_zuojiantou = findViewById(R.id.iv_geren_zuojiantou);
        rl_nicheng = findViewById(R.id.rl_nicheng);

        Intent intent = getIntent();
        String icon = intent.getStringExtra("icon");
        String nickname = intent.getStringExtra("nickname");
        String mobile = intent.getStringExtra("mobile");
       /* Glide.with(CenterActivity.this).load(icon)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).dontAnimate().into(iv_geren_touxiang);*/
        RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
        Glide.with(CenterActivity.this).load(icon)
                .apply(options).into(iv_geren_touxiang);
        tv_geren_name.setText(mobile);
        tv_geren_nicheng.setText(nickname);
        initOnClick();
    }



    private void initOnClick() {
        iv_geren_zuojiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_geren_touxiang.setOnClickListener(new View.OnClickListener() {
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
                        .singleSelect()
                        //剪切
                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                       // .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(list)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(CenterActivity.this, imageConfig);   // 开启图片选择器
            }
        });

        rl_nicheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final View inflate= LayoutInflater.from(CenterActivity.this).inflate(R.layout.alertdialog, null);
               AlertDialog.Builder a=new AlertDialog.Builder(CenterActivity.this);
                a.setView(inflate).setTitle("修改昵称").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText et_nicname=inflate.findViewById(R.id.et_nicname);
                        String s = et_nicname.getText().toString();
                        presenter.UpdateNickNamePresentSuccess(MyInterceptor.id,s);
                         tv_geren_nicheng.setText(s);
                    }
                });
                a.create().show();
            }
        });
    }
    @Override
    public void UploadSuccess(SendBean value) {

        Toast.makeText(this, value.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UploadFailue(String msg) {

    }

    @Override
    public void UploadError(Throwable e) {

    }

    public static final int REQUEST_CODE = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String s : pathList) {
                s1 = s.toString();
            }
            list.clear();
            list.addAll(pathList);
           /* Glide.with(CenterActivity.this).load(s1)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).dontAnimate().into(iv_geren_touxiang);*/
            RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
            Glide.with(CenterActivity.this).load(s1)
                    .apply(options).into(iv_geren_touxiang);
            presenter.UploadPresentSuccess(MyInterceptor.id,list);
        }
    }
}
