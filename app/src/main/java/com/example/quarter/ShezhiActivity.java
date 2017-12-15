package com.example.quarter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.base.BaseActivity;
import com.example.quarter.bean.VersionBean;
import com.example.quarter.myapp.MyApp;
import com.example.quarter.presenter.UpdatePresenter;
import com.example.quarter.utils.ClearCache;
import com.example.quarter.view.UpdateView;

import java.io.File;

public class ShezhiActivity extends BaseActivity<UpdatePresenter> implements UpdateView {

    private TextView tv_fanhui;
    private Button bu_tuichu;
    private TextView tv_daxiao;
    private RelativeLayout rl_deleter;
    private RelativeLayout rl_deleter1;
    private RelativeLayout rl_update;


    @Override
    public UpdatePresenter initPresenter() {
        UpdatePresenter updatePresenter=new UpdatePresenter(this);
        return updatePresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shezhi;
    }

    @Override
    public void initView() {
        init();
        initonClick();
    }

    private void initonClick() {
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bu_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences token = MyApp.context.getSharedPreferences("token", Context.MODE_PRIVATE);
                token.edit().clear().commit();
                SharedPreferences uid = MyApp.context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                uid.edit().clear().commit();
                SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(ShezhiActivity.this,MainActivity.class));
                finish();
            }
        });
        rl_deleter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearCache.clearAllCache(ShezhiActivity.this);
                AlertDialog.Builder a=new AlertDialog.Builder(ShezhiActivity.this)
                        .setMessage("是否清除?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
                                    tv_daxiao.setText(totalCacheSize);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                a.create().show();
            }
        });

        rl_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.UpdateSuccessPresenter();
            }
        });
    }

    private void init() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        bu_tuichu = findViewById(R.id.bu_tuichu);
        tv_daxiao = findViewById(R.id.tv_daxiao);
        rl_deleter1 = findViewById(R.id.rl_deleter);
        rl_update = findViewById(R.id.rl_update);
        try {
            String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
            tv_daxiao.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateSuccess(VersionBean versionBean) {
        System.out.println("APK"+versionBean.getData().getApkUrl());
    }

    @Override
    public void UpdateFailue(String msg) {
        if(msg.equals("1"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        if(msg.equals("2"))
        {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent=new Intent(ShezhiActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void UpdateError(Throwable e) {

    }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);

        initView();
        initonClick();
    }

    private void initonClick() {
        tv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bu_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences token = MyApp.context.getSharedPreferences("token", Context.MODE_PRIVATE);
                token.edit().clear().commit();
                SharedPreferences uid = MyApp.context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                uid.edit().clear().commit();
                SharedPreferences sp = getSharedPreferences("ZHI", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(ShezhiActivity.this,MainActivity.class));
                finish();
            }
        });
        rl_deleter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearCache.clearAllCache(ShezhiActivity.this);
                AlertDialog.Builder a=new AlertDialog.Builder(ShezhiActivity.this)
                        .setMessage("是否清除?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
                                    tv_daxiao.setText(totalCacheSize);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                a.create().show();

            }
        });
    }

    private void initView() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        bu_tuichu = findViewById(R.id.bu_tuichu);
        tv_daxiao = findViewById(R.id.tv_daxiao);
        rl_deleter1 = findViewById(R.id.rl_deleter);
        rl_update = findViewById(R.id.rl_update);
        try {
            String totalCacheSize = ClearCache.getTotalCacheSize(ShezhiActivity.this);
            tv_daxiao.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
