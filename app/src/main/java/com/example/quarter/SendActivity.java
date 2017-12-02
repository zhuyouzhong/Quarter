package com.example.quarter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.adapter.MySendRecycleView;
import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.SendBean;
import com.example.quarter.presenter.SendPersenter;
import com.example.quarter.public_class.GlideLoader;
import com.example.quarter.utils.MyInterceptor;
import com.example.quarter.view.SendView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class SendActivity extends BaseActivity<SendPersenter> implements SendView {


    private TextView tv_quxiao;
    private TextView tv_send;
    private EditText et_center;
    private RecyclerView rlv_iv;
    private ImageView iv_shangchuan;
    private ArrayList<String> path = new ArrayList<>();
    private MySendRecycleView mySendRecycleView;



    @Override
    public SendPersenter initPresenter() {
        SendPersenter sendPersenter=new SendPersenter(this);
        return sendPersenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_send;
    }

    @Override
    public void initView() {
        rlv_iv = findViewById(R.id.rlv_iv);
        iv_shangchuan = findViewById(R.id.iv_shangchuan);
        tv_quxiao = findViewById(R.id.tv_quxiao);
        tv_send = findViewById(R.id.tv_send);
        et_center = findViewById(R.id.et_center);

        initOkClick();
    }

    private void initOkClick() {
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String center = et_center.getText().toString();
                if(TextUtils.isEmpty(center))
                {
                    Toast.makeText(SendActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(path.size()==0)
                {
                    presenter.SendPresenterSuccess(MyInterceptor.id,center,null);
                }
                else
                {
                    presenter.SendPresenterSuccess(MyInterceptor.id,center,path);
                }

                ProgressDialog mypDialog=new ProgressDialog(SendActivity.this);
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
        iv_shangchuan.setOnClickListener(new View.OnClickListener() {
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
                                     .mutiSelect()
                                    //剪切
                                     //.crop()
                                       // 多选时的最大数量   （默认 9 张）
                                     .mutiSelectMaxSize(9)
                                       // 已选择的图片路径
                                      .pathList(path)
                                       // 拍照后存放的图片路径（默认 /temp/picture）
                                      .filePath("/ImageSelector/Pictures")
                                      // 开启拍照功能 （默认开启）
                                     .showCamera()
                                     .requestCode(REQUEST_CODE)
                                    .build();
                ImageSelector.open(SendActivity.this, imageConfig);   // 开启图片选择器
                mySendRecycleView = new MySendRecycleView(path,SendActivity.this);
                rlv_iv.setLayoutManager(new GridLayoutManager(SendActivity.this,3));
                rlv_iv.setAdapter(mySendRecycleView);
            }

        });
    }

    @Override
    public void SendSuccess(SendBean value) {
            Toast.makeText(this, value.getMsg(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(SendActivity.this,SendSuccessActivity.class);
            startActivity(intent);
            finish();
    }

    @Override
    public void SendFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(SendActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void SendError(Throwable e) {

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
            mySendRecycleView.notifyDataSetChanged();
        }
        for (String s : path) {
            System.out.println("----图片路径----"+s.toString());

        }
        Toast.makeText(this, ""+path.size(), Toast.LENGTH_SHORT).show();
    }
}
